<template>
	<view class="main">
		<view class="num">
			<view class="top">
				<view class=""><image :src="'http://localhost:8080///image/' + list.hotel.img" mode="center"></image></view>
				<view class="index">
					<view class="item">{{ list.hotel.name }}({{ list.roomType.name }})</view>
					<view class="item">{{ $u.timeFrom(list.note.startTime, 'yyyy年mm月dd日') }}入住</view>
				</view>
			</view>
			<view class="bottom"><u-rate size="65" :count="count" v-model="value"></u-rate></view>
		</view>
		<view class="text1"><textarea v-model="con" placeholder="点击输入评价" /></view>
		<view class="but" @click="go()">提交</view>
		<u-toast ref="uToast" />
	</view>
</template>

<script>
export default {
	data() {
		return {
			list: '',
			count: 5,
			value: 0,
			con: ''
		};
	},
	onLoad(e) {
		var that = this;
		console.log(e);
		uni.getStorage({
			key: 'list',
			success: function(res) {
				console.log(res.data);
				that.list = res.data;
			}
		});
	},
	methods: {
		go() {
			console.log('1');
			var that = this;

			if (that.value != '') {
				if (that.con != '') {
					uni.request({
						url: 'http://localhost:8080//estimate/addWechat',
						data: {
							id: that.list.note.id,
							value: that.value,
							con: that.con
						},
						method: 'POST',
						success: res => {
							this.$refs.uToast.show({
								title: '评论成功',
								type: 'success',
								url: '/pages/index/index'
							});
							setTimeout(function() {
								uni.switchTab({
									url: '/pages/index/index'
								});
							}, 1000);
						}
					});
				}
			} else {
				this.$refs.uToast.show({
					title: '请评分\输入评价',
					type: 'error',
					url: '/pages/user/index'
				});
			}
		}
	}
};
</script>

<style lang="scss">
body {
	background-color: #f0f0f0;
}
.main {
	width: 90%;
	margin-left: 5%;
}
.num {
	width: 100%;
	height: 350upx;
	background-color: #ffffff;
	border-radius: 35upx;
	margin-top: 30upx;
	margin-bottom: 30upx;
}
.top {
	height: 160upx;
	width: calc(100% - 40upx);
	margin: 0 20upx;
	padding-top: 20upx;
	display: flex;
	image {
		width: 120upx;
		height: 120upx;
		margin-right: 20upx;
	}
	.index {
		.item {
			font-weight: bold;
			height: 50%;
			font-size: 32upx;
		}
	}
}
.bottom {
	width: 100%;
	height: 190upx;
	display: flex;
	justify-content: center;
	align-items: center;
}
.text1 {
	width: 100%;
	height: 300upx;
	background-color: #ffffff;
	border-radius: 35upx;
	padding: 20upx;
	textarea {
		height: 260upx;
		width: 100%;
	}
}
.but {
	width: 220upx;
	background-color: #416699;
	color: #ffffff;
	text-align: center;
	line-height: 100upx;
	font-size: 50upx;
	font-weight: bold;
	height: 100upx;
	border-radius: 35upx;
	position: fixed;
	left: 253upx;
	bottom: 50upx;
}
</style>
