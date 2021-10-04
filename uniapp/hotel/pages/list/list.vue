<template>
	<view class="main">
		<view class="search"><u-search :show-action="true" action-text="搜索" :animation="true"></u-search></view>
<!-- 		<view class="">
			<u-dropdown>
				<u-dropdown-item v-model="value1" title="距离" :options="options1"></u-dropdown-item>
				<u-dropdown-item v-model="value2" title="温度" :options="options2"></u-dropdown-item>
			</u-dropdown>
		</view> -->
		<view class="index">
			<view class="item" @click="toInfo(index)" v-for="(item,index) in hotelList" :key="item.value">
				<view class="left"><image :src="'http://localhost:8080//image/'+item.img " mode=""></image></view>
				<view class="right">
					<text class="text-name">{{item.name}}</text>
					<text class="text-address">{{item.addres}}</text>
					<view class="text-img">
						{{item.assignmark}}
						<image src="../../static/icon/star.png" mode=""></image>
					</view>
					<view class="text-img">
						<uni-tag :text=item.wait2 type="primary" style="margin: 2upx;"></uni-tag>
						<uni-tag :text=item.level type="primary" style="margin: 2upx;"></uni-tag>

					</view>
					
				</view>

			</view>
		</view>
	</view>
</template>

<script>
export default {
	data() {
		return {
			keyword: '',
			value1: 1,
			value2: 2,
			options1: [
				{
					label: '默认排序',
					value: 1
				},
				{
					label: '距离优先',
					value: 2
				},
				{
					label: '价格优先',
					value: 3
				}
			],
			options2: [
				{
					label: '去冰',
					value: 1
				},
				{
					label: '加冰',
					value: 2
				}
			],
			hotelList: ''
		};
	},
	methods: {
		toInfo(e) {
			console.log(e)
			uni.navigateTo({
				url: '../info/info?id='+e
			});
		}
	},
	onLoad() {
		var that = this;
		uni.getStorage({
			key: 'hotelList',
			success: function(res) {
				that.hotelList = res.data;
			}
		});
	}
};
</script>

<style lang="scss">
.search {
	width: 90%;
	margin-left: 5%;
}
u-search {
	width: 90%;
	margin-left: 5%;
}
.main {
	width: 100%;

	margin-top: 50upx;
}
.index {
	width: 90%;
	margin: 0 5%;
}
.item {
	width: 100%;
	height: 200upx;
	padding: 20upx;
	margin-top: 50upx;
	margin-bottom: 30upx;
	border-radius: 35upx;
	background: #ffffff;
	box-shadow: -8px 8px 35px #d9d9d9, 8px -8px 35px #ffffff;
	display: flex;
}
.left {
	width: 30%;
	height: 100%;
	padding-right: 20upx;
	image {
		width: 100%;
		height: 100%;
		border-radius: 20upx;
	}
}
.right {
	width: 70%;
	height: 100%;
	display: flex;
	flex-direction: column;
	position: relative;
	image {
		width: 30upx;
		height: 30upx;
	}
}
.text-name {
	font-size: 32upx;
	font-weight: bold;
}
.text-num {
	font-size: 28upx;
	color: #416699;
}
.text-address {
	font-size: 24upx;
	color: #777777;
	line-height: 60upx;
}
.text-price {
	font-size: 40upx;
	color: red;
	position: absolute;
	width: 100%;
	right: 30upx;
	bottom: 20upx;
	width: auto;
}
.text-img {
	display: flex;
	align-items: center;
	font-size: 30upx;
	line-height: 50upx;
}

</style>
