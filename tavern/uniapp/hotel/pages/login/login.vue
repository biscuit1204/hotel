<template>
	<view>
		<view class="text">
			探寻</br>
			梦想中的住所
		</view>
		<view class="back"><image mode="top" src="../../static/image/back.jpg"></image></view>
		<view class="button" @click="login">登录</view>
	</view>
</template>

<script>
export default {
	data() {
		return {};
	},
	methods: {
		login() {
			wx.getUserProfile({
				desc: '用于完善会员资料', // 声明获取用户个人信息后的用途，后续会展示在弹窗中，请谨慎填写
				success: e => {
					console.log(e);
					wx.login({
						success(res) {
							if (res.code) {
								//发起网络请求
								uni.request({
									url: 'http://localhost:8080//user/login',
									data: {
										code: res.code
									},
									success: res => {
										console.log(res.data);
										if (res.data.id == null) {
											uni.request({
												url: 'http://localhost:8080//user/AddUser',
												data: {
													name: e.userInfo.nickName,
													img: e.userInfo.avatarUrl,
													openKey:res.data.openKey,
													sex:e.userInfo.gender
												},
												method:"POST",
												success: res => {
													uni.setStorage({
													    key: 'e.userInfo',
													    data: e.userInfo,
													    success: function () {
													uni.switchTab({
														url: '../index/index'
													});
													    }
													});
												}
											});
										} else {
													uni.setStorage({
													    key: 'e.userInfo',
													    data: res.data,
													    success: function () {
													uni.switchTab({
														url: '../index/index'
													});
													    }
													});
										}
									}
								});
							} else {
								console.log('登录失败！' + res.errMsg);
							}
						}
					});
				}
			});
		}
	}
};
</script>

<style lang="scss">
.text {
	color: #ffffff;
	position: fixed;
	top: 200upx;
	left: 5%;
	font-size: 70upx;
}
.back {
	width: 100%;
	height: 100vh;
	position: fixed;
	top: 0;
	z-index: -1;
	image {
		width: 100%;
		height: 100%;
	}
}
.button {
	width: 250upx;
	height: 100upx;
	background-color: #fcfcfc;
	position: fixed;
	bottom: 80upx;
	left: 50%;
	transform: translateX(-50%);
	border-radius: 40upx;
	color: #416699;
	font-size: 50upx;
	line-height: 100upx;
	text-align: center;
}
</style>
