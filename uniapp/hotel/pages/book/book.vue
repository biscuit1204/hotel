<template>
	<view>
		<u-modal v-model="show" :content="content" show-cancel-button="true" @confirm="yes"></u-modal>
		<u-toast ref="uToast" />

		<view class="empty" v-if="list.length == 0"><u-empty text="没有订单" mode="order"></u-empty></view>
		<view class="main">
			<view class="item">
				<view class="index" v-for="(item, index) in list" :key="item.value" @longpress="longTime(index)">
					<view class="tag">
						<u-tag text="未出行" type="success" v-if="item.note.flag == 0" />
						<u-tag text="出行中" type="warning" v-if="item.note.flag == 1" />
						<u-tag text="行程结束" type="info" v-if="item.note.flag == 2" />
					</view>
					<view>订单号：{{ item.note.id }}</view>
					<view class="room">
						<view class="room-left"><image :src="'http://localhost:8080//image/' + item.hotel.img" mode="center"></image></view>
						<view class="room-right">
							<view class="">{{ item.roomType.name }}({{item.room.name}})</view>
							<view class="">￥{{ item.note.price }}</view>
						</view>
					</view>
					<view class="text">
						<view class="text-hotel-name">酒店地址：{{ item.hotel.address }}</view>
						<view class="">预订时间：{{ $u.timeFrom(item.note.startTime, 'yyyy年mm月dd日') }}</view>
					</view>
					<view class="but"><view class="butt" @click="go(index)">到这去</view></view>
				</view>
			</view>
		</view>
	</view>
</template>

<script>
export default {
	data() {
		return {
			userid: '',
			list: '',
			show: false,
			content: '是否删除此订单',
			id: ''
		};
	},
	onShow() {
		var that = this;
		uni.getStorage({
			key: 'e.userInfo',
			success: function(res) {
				uni.request({
					url: 'http://localhost:8080//note/noteList',
					data: {
						userid: res.data.id
					},
					method: 'POST',
					success: res => {
						that.list = res.data;
					}
				});
			}
		});
	},
	methods: {
		go(e) {
			var that = this;
			console.log(e);

			uni.openLocation({
				latitude: Number(that.list[e].hotel.lat),
				longitude: Number(that.list[e].hotel.lng),
				success: function() {
					console.log('success');
				}
			});
		},
		longTime(e) {
			this.show = true;

			this.id = this.list[e].note.id;
		},
		yes() {
			var that = this;
			uni.request({
				url: 'http://localhost:8080//note/delWechat',
				data: {
					id: that.id
				},
				method: 'POST',
				success: res => {
					this.$refs.uToast.show({
						title: '删除成功',
						type: 'success',
						url: '/pages/user/index'
					});
					var that = this;
					uni.getStorage({
						key: 'e.userInfo',
						success: function(res) {
							uni.request({
								url: 'http://localhost:8080//note/noteList',
								data: {
									userid: res.data.id
								},
								method: 'POST',
								success: res => {
									that.list = res.data;
								}
							});
						}
					});
				}
			});
		}
	}
};
</script>

<style lang="scss">
.empty {
	width: 100%;
	height: 100vh;
}
.main {
	width: 90%;
	margin-left: 5%;
	.index {
		width: 100%;
		height: 450upx;
		border-radius: 35upx;
		margin-top: 10upx;
		background: #ffffff;
		padding: 35upx;
		border-radius: 35upx;
		background: #ffffff;
		box-shadow: -8px 8px 35px #d9d9d9, 8px -8px 35px #ffffff;
		background-color: #fcfcfc;
		position: relative;
		margin-bottom: 50upx;
	}
	.room {
		width: 100%;
		height: 150upx;
		margin: 20upx 0;
		display: flex;
		.room-left {
			width: 35%;
			height: 100%;
			image {
				width: 100%;
				height: 100%;
			}
		}
		.room-right {
			margin-left: 5%;
			width: 60%;
			height: 100%;
			font-weight: bold;
			font-size: 32upx;
			background-color: #ffffff;
			view {
				height: 50%;
			}
		}
	}
}
.text {
	font-size: 32upx;
	view {
		height: 50upx;
	}
}
.but {
	height: 80upx;
	position: absolute;
	right: 20upx;
	.butt {
		width: 150upx;
		height: 100%;
		border-radius: 35upx;
		background-color: #416699;
		color: #ffffff;
		line-height: 80upx;
		text-align: center;
		font-size: 33upx;
	}
}
.text-hotel-name {
	overflow: hidden;
	white-space: nowrap;
	text-overflow: ellipsis;
}
.tag {
	position: absolute;
	top: 20upx;
	right: 20upx;
	z-index: 2;
}
</style>
