<template>
	<view>
		<view class="main">
			<view class="item" v-for="(item, index) in list" :key="item.value">
				<view class="left"><image :src="'http://localhost:8080//image/' + item.img" mode=""></image></view>
				<view class="center">
					<title>{{ item.name }}</title>
					<view class="center-item">
						<u-tag text="WiFi" type="success" :v-if="item.wifi == '有'" />
						<u-tag :text="item.bed" type="success" />
						<u-tag text="有窗" type="success" :v-if="item.windows == '有'" />
						<u-tag :text="item.price" type="error" />
					</view>
				</view>
				<view class="right" @click="open(index)">预订</view>
			</view>
			<u-modal v-model="show" @confirm="book" :content="content" show-cancel-button="true" :mask-close-able="true"></u-modal>
		</view>
			<u-top-tips ref="uTips"></u-top-tips>

	</view>
</template>

<script>
export default {
	data() {
		return {
			time: '',
			list: '',
			show: false,
			content: '是否确认预订？',
			id: '',
			userid:''
		};
	},
	onLoad(e) {
		var that = this;
		uni.getStorage({
			key: 'date',
			success: function(res) {
				that.time = res;
				uni.request({
					url: 'http://localhost:8080//roomType/searchRoomType',
					method: 'POST',
					data: {
						hotel: e.id,
						date: res.data
					},
					success: res => {
						that.list = res.data;
						
					}
				});
			}
		});
		uni.getStorage({
			key: 'e.userInfo',
			success: function(res) {
				that.userid = res.data.id;
			}
		});
		console.log(e.id);
	},
	methods: {
		open(e) {
			this.show = true;
			this.id = e;
		},
		book() {
			console.log(this.id);
			var that = this;
			uni.request({
				url: 'http://localhost:8080//room/bookHome',
				method: 'POST',
				data: {
					id: that.list[that.id].id,
					date:that.time.data,
					userid:that.userid
				},
				success: res => {
					console.log(res.data)
					uni.removeStorage({
					    key: 'date',
					    success: function (res) {
					        console.log('success');
					    }
					});
					if(res.data=='预订成功'){
					this.$refs.uTips.show({
						title: res.data,
						type: 'success',
						duration: '2300'
					})
					      setTimeout(function() {
							uni.switchTab({
							    url: '/pages/index/index'
							});
					   }, 2300);


					}else{
						this.$refs.uTips.show({
							title: res.data,
							type: 'error',
							duration: '2300'
						})		
					      setTimeout(function() {
							uni.switchTab({
							    url: '/pages/index/index'
							});
					   }, 2300);

					}

				}
			});
		}
	}
};
</script>

<style lang="scss">
.main {
	width: 90%;
	margin-left: 5%;
	.item {
		width: 100%;
		border-radius: 35upx;
		height: 200upx;
		background: #ffffff;
		box-shadow: -8px 8px 35px #d9d9d9, 8px -8px 35px #ffffff;
		display: flex;
		margin: 20upx 0;
		padding: 20upx;
		margin-right: 10upx;
		align-items: center;
		.left {
			width: 30%;
			height: 100%;
			margin-right: 10upx;
			image {
				width: 100%;
				height: 100%;
				border-radius: 35upx;
			}
		}
		.center {
			width: 50%;
			height: 100%;
			title {
				font-size: 32upx;
				margin-bottom: 10upx;
			}
			.center-item {
				margin-top: 10upx;
				.u-tag {
					margin: 5upx;
				}
			}
		}
		.right {
			width: 20%;
			height: 50upx;
			border-radius: 35upx;
			background-color: #007aff;
			line-height: 50upx;
			color: #ffffff;
			text-align: center;
		}
	}
}
</style>
