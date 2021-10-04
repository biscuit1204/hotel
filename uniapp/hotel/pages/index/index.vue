<template>
	<view class="content">
		<view class="titleText">预订</view>
		<view class="back">
			<swiper autoplay="true" interval="3000" duration="1000">
				<swiper-item><image src="../../static/image/1.jpg" mode="center"></image></swiper-item>
				<swiper-item><image src="../../static/image/2.jpg" mode="center"></image></swiper-item>
			</swiper>
		</view>
		<view class="main">
			<view class="item">
				<view class="item-text" @click="getAddress" >
					<view class="text">
						
						<view class="text1">{{ address }}</view>
						<image src="../../static/icon/address.png" mode=""></image>
					</view>
				</view>
				<view class="item-text">
					<view class="text" @click="showDate" v-if="!dateNow">
						{{ date }}
						<u-calendar max-date="2050-1-1" v-model="flag" :mode="mode" @change="change"></u-calendar>
						<image src="../../static/icon/right.png" mode=""></image>
					</view>
					<view class="text" @click="showDate" v-if="dateNow">
						<view class="textDate">
							{{ dateNow.startMonth }}月{{ dateNow.startDay }}日
							<text class="week">（{{ dateNow.startWeek }})</text>
							→ {{ dateNow.endMonth }}月{{ dateNow.endDay }}日
							<text class="week">（{{ dateNow.endWeek }})</text>
						</view>
						<u-calendar max-date="2050-1-1" v-model="flag" :mode="mode" @change="change"></u-calendar>
						<!-- <image src="../../static/icon/right.png" mode=""></image> -->
					</view>
				</view>
				<view class="item-text">
					<view class="text"><input type="text" v-model="search1"   placeholder="通过关键词搜索酒店" /></view>
				</view>
<!-- 				<view class="item-text">
					<view class="text" @click="showPrice">
						筛选酒店品牌/价格
						<u-popup mask-close-able="false" @close="close" mode="bottom" closeable="true" v-model="show">
							<view class="u-popupView">
								<view class="u-popupView-main">
									<view class="price">
										<text>价格区间</text>
										<view class="inpu">
											<input type="text" value="" placeholder="自定义最低价" />
											-
											<input type="text" value="" placeholder="自定义最高价" />
										</view>
									</view>
									<view class="price">
										<text>品牌选择</text>
										<view class="inpu">
											<uni-data-checkbox mode="tag" :multiple="true" v-model="value" :localdata="range" @change="change"></uni-data-checkbox>
										</view>
									</view>
									<view class="price1" >
										<view class="button1" @click="ok">
											确认
										</view>
									</view>
								</view>
							</view>
						</u-popup>
						<image src="../../static/icon/right.png" mode=""></image>
					</view>
				</view> -->
				<view class="item-text1">
					<view class="button" @click="search">
						搜索
					</view>
					
				</view>
			</view>
		</view>
		<u-toast ref="uToast" />
	</view>
</template>

<script>
export default {
	data() {
		return {
			title: 'Hello',
			address: '',
			show: false,
			mode: 'range',
			date: '请选择入住/退房时间',
			dateNow: '',
			flag: false,
			value: 0,
			range: [{ value: 0, text: '篮球' }, { value: 1, text: '足球' }, { value: 2, text: '游泳' }],
			lat:'',
			lng:'',
			search1:'',
			city:''
		};
	},
	onLoad() {},
	methods: {
		getAddress() {
			var that = this;
			console.log("111")
			        wx.chooseLocation({
			            latitude: that.lat,
			            longitude: that.lng,
			            success: function (e) {	
							uni.request({
								url: 'https://apis.map.qq.com/ws/geocoder/v1/',
								data: {
									location: e.latitude + ',' + e.longitude,
									key: '逆地址解析(位置描述)'
								},
								method: 'GET',
								success: res => {
									console.log(res);
									that.city=res.data.result.ad_info.city;
									that.address = res.data.result.address;
									that.lat=res.data.result.ad_info.location.lat,
									that.lng=res.data.result.ad_info.location.lng
								}
							});
			            }
			        });
		},
		getLocation() {
			var that = this;
			uni.getLocation({
				type: 'gcj02',
				success: function(res) {
					uni.request({
						url: 'https://apis.map.qq.com/ws/geocoder/v1/',
						data: {
							location: res.latitude + ',' + res.longitude,
							key: 'T4MBZ-MCVK6-W6QS3-MCTE4-VFEEZ-LZBGJ'
						},
						method: 'GET',
						success: res => {
							console.log(res);
							that.city=res.data.result.ad_info.city;
							that.address = res.data.result.address;
							that.lat=res.data.result.ad_info.location.lat,
							that.lng=res.data.result.ad_info.location.lng
						}
					});
				}
			});
		},
		change(e) {
			console.log(e)
			this.dateNow = e;
			uni.setStorage({
			    key: 'date',
			    data: e,
			    success: function () {
			        console.log('success');
			    }
			});
		},
		showDate() {
			this.flag = true;
		},
		showPrice() {
			this.show = true;
		},
		close() {
			this.show = false;
		},
		search(){
			
			if(this.dateNow!=""){
				uni.request({
				    url: 'http://localhost:8080//hotel/searchHotel', 
					method:"POST",
				    data: {
				        address:this.address,
						lat:this.lat,
						lng:this.lng,
						date:this.dateNow,
						search:this.search1,
						city:this.city
				    },
				    success: (res) => {
						uni.setStorage({
						    key: 'hotelList',
						    data: res.data,
						    success: function () {
						        uni.navigateTo({
						            url: '../list/list'
						        });
						    }
						});
				    }
				});
			}else{
				this.$refs.uToast.show({
									title: '请选择入住时间',
									type: 'error',
									
								})
			}
			

		},
		ok(){
			this.show = false;
		}
	},
	onLoad: function() {
		this.getLocation();
	}
};
</script>

<style lang="scss">
.back {
	width: 100%;
	height: 35vh;
	position: fixed;
	top: 0;
	z-index: -1;
	swiper {
		width: 100%;
		height: 100%;
		swiper-item {
			width: 100%;
			height: 100%;
			image {
				width: 100%;
				height: 100%;
			}
		}
	}
}
.main {
	position: fixed;
	top: 28vh;
	background-color: #ffffff;
	width: 90%;
	margin: 0 5%;
	height: 40vh;
	border-radius: 35upx;
	background: #ffffff;
	box-shadow: -8px 8px 35px #d9d9d9, 8px -8px 35px #ffffff;
	.item {
		margin: 0 35upx;
		width: calc(100% - 70upx);
		height: 100%;
		// background-color: blue;
		.item-text {
			height: calc(25% - 1upx);
			width: 100%;
			border-bottom: solid #f0f0f0 1upx;
			.text {
				font-size: 32upx;
				width: 100%;
				height: 100%;
				padding-top: 4%;
				display: flex;
				align-items: center;
				justify-content: space-between;

				image {
					width: 40upx;
					height: 40upx;
				}
			}
		}
	}
}
.textDate {
	font-size: 32upx;

	display: flex;
	align-items: center;
	vertical-align: bottom;
}
.week {
	font-size: 24upx;
	color: #777;
}
.u-popupView {
	height: 35vh;
}
.titleText {
	color: #ffffff;
	position: fixed;
	top: 200upx;
	left: 5%;
	font-size: 100upx;
}
.u-popupView-main {
	margin: 5%;
	height: 100%;
	width: 90%;
}
.price {
	margin-bottom: 50upx;
	text {
		font-size: 36upx;
	}
	.inpu {
		font-size: 32upx;
		margin: 20upx 0;
		display: flex;
		justify-content: space-between;
		input {
			width: 40%;
			border-radius: 35upx;
			background-color: #f0f0f0;
			padding: 10upx 15upx;
		}
	}
}
.button {
	width: 350upx;
	height: 100upx;
	background-color: #416699;
	border-radius: 40upx;
	color: #fcfcfc ;
	font-size: 50upx;
	line-height: 100upx;
	text-align: center;
}
.button1 {
	width: 250upx;
	height: 80upx;
	background-color: #416699;
	border-radius: 40upx;
	color: #fcfcfc ;
	font-size: 36upx;
	line-height: 80upx;
	text-align: center;
}
.item-text1{
	height:25%;
	width: 100%;
	border: none;
	display: flex;
	justify-content: center;
	align-items: center;
}
.price1{
	display: flex;
	justify-content: center;
	align-items: center;
}
				.text1{
					width: 400upx;
					overflow: hidden;
					text-overflow:ellipsis;
					white-space: nowrap;
					max-width:400upx ;
				}
</style>
