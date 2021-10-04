<template>
	<view>
		<view class="back"></view>
		<view class="main">
			<view class="title">
				<image :src=user.img mode="center"></image>
				<view class="name">
					<view class="">{{user.name}}</view>
					<view class="">{{user.id}}</view>
				</view>
			</view>
			<view class="vip-item">
				<view class="text">
					<view class="text-item">
						<text>{{user.vip}}</text>
						<image src="../../static/icon/star.png" mode=""></image>
					</view>
					<view class="text-item1">
						<view class="text-item1-item1">
							<view class="icon">vip4</view>
							<view class="vip-text">
								30
								<image src="../../static/icon/star.png" mode=""></image>
							</view>
						</view>
						<view class="text-item1-item2">
							<view class="icon">vip3</view>
							<view class="vip-text">
								10
								<image src="../../static/icon/star.png" mode=""></image>
							</view>
						</view>
						<view class="text-item1-item3">
							<view class="icon">vip2</view>
							<view class="vip-text">
								3
								<image src="../../static/icon/star.png" mode=""></image>
							</view>
						</view>
						<view class="text-item1-item4">
							<view class="icon">vip1</view>
							<view class="vip-text">
								0
								<image src="../../static/icon/star.png" mode=""></image>
							</view>
						</view>
					</view>
				</view>
			</view>
			<view class="fun">
				<view class="fun-item" @click="goto">
					<image src="../../static/icon/history.png" mode=""></image>
					历史订单
				</view>
				<view class="fun-item">
					<image src="../../static/icon/tel.png" mode=""></image>

					<button style="border: none; background-color: #ffffff;margin-left: 0;padding-left: 0;color: #707070;" type="default" open-type="contact">联系我们</button>
				</view>
				<view class="fun-item" @click="open">
					<image src="../../static/icon/logout.png" mode=""></image>
					退出登录
					<view><u-modal show-cancel-button="true" v-model="show" show-title="true" :mask-close-able="true" :content="content"></u-modal></view>
				</view>
			</view>
		</view>
	</view>
</template>

<script>
export default {
	data() {
		return {
			show: false,
			content: '是否确认退出？',
			user:'',
			vip:"vip4"
		};
	},
	methods: {
		open() {
			this.show = true;
		},
		goto(){
			uni.navigateTo({
				url:"../noteHistory/noteHistory"
			})
		}
	},
	onLoad() {
		var that = this
		uni.getStorage({
		    key: 'e.userInfo',
		    success: function (res) {
		        that.user=res.data
				if(that.user.vip<30){
					that.vip="vip3"
				}
				if(that.user.vip<10){
					that.vip="vip2"
				}
				if(that.user.vip<3){
					that.vip="vip1"
				}
		    }
		});
	}
};
</script>

<style lang="scss">
.back {
	width: 100%;
	height: 35vh;
	position: fixed;
	top: 0;
	background-color: #7953e7;
	z-index: -1;
}
.main {
	width: 90%;
	margin: 0 5%;

	margin-top: 180upx;
	.title {
		height: 100upx;
		width: 100%;
		display: flex;
		image {
			height: 100upx;
			width: 100upx;
			border-radius: 50upx;
			margin: 0 30upx;
		}
		.name {
			color: #ffffff;
			font-size: 36upx;
		}
	}
	.vip-item {
		margin-top: 100upx;
		height: 300upx;
		width: 100%;
		border-radius: 35upx;
		background: #ffffff;
		box-shadow: -8px 8px 35px #d9d9d9;
		.text {
			padding: 35upx;
			height: 100%;
			width: 100%;
		}
	}
}
.text-item {
	width: 100%;
	height: 50%;
	font-size: 60upx;
	display: flex;
	align-items: center;
	image {
		height: 80upx;
		width: 80upx;
	}
}
.text-item1 {
	width: 100%;
	height: 50%;
	display: flex;
	.text-item1-item1 {
		height: 100%;
		width: 20%;
		margin: 0 2.5%;
		display: flex;
		justify-content: center;
		flex-direction: column;
		align-items: center;
		.icon {
			height: 80upx;
			width: 80upx;
			background-color: #7953e7;
			border-radius: 50%;
			line-height: 80upx;
			text-align: center;
			color: #fff;
		}
	}
}
.vip-text {
	image {
		height: 20upx;
		width: 20upx;
	}
}
.text-item1-item2 {
	height: 100%;
	width: 20%;
	margin: 0 2.5%;
	display: flex;
	justify-content: center;
	flex-direction: column;
	align-items: center;
	.icon {
		height: 80upx;
		width: 80upx;
		background-color: #c5b9fe;
		border-radius: 50%;
		line-height: 80upx;
		text-align: center;
		color: #fff;
	}
}
.text-item1-item3 {
	height: 100%;
	width: 20%;
	margin: 0 2.5%;
	display: flex;
	justify-content: center;
	flex-direction: column;
	align-items: center;
	.icon {
		height: 80upx;
		width: 80upx;
		background-color: #f9df95;
		border-radius: 50%;
		line-height: 80upx;
		text-align: center;
		color: #fff;
	}
}
.text-item1-item4 {
	height: 100%;
	width: 20%;
	margin: 0 2.5%;
	display: flex;
	justify-content: center;
	flex-direction: column;
	align-items: center;
	.icon {
		height: 80upx;
		width: 80upx;
		background-color: #c4c2da;
		border-radius: 50%;
		line-height: 80upx;
		text-align: center;
		color: #fff;
	}
}
.fun {
	.fun-item {
		margin-top: 50upx;
		width: 100%;
		height: 120upx;
		border-radius: 35upx;
		background: #ffffff;
		box-shadow: -8px 8px 35px #d9d9d9;
		display: flex;
		align-items: center;
		font-size: 32upx;
		color: #707070;
		image {
			width: 40upx;
			height: 40upx;
			margin: 0 30upx;
		}
	}
}
button::after {
	border: none;
}
</style>
