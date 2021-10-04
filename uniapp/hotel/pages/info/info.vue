<template>
	<view>
		<view class="back"><image :src="'http://localhost:8080//image/'+hotel.img " mode="top"></image></view>
		<view class="main">
			<title>{{hotel.name}}</title>
			<view class="num">
				<image src="../../static/icon/star.png" mode=""></image>
				{{hotel.assignmark}}
			</view>
			<view class="text">{{hotel.note}}</view>
			<view class="map"><map :latitude="latitude" :longitude="longitude" :markers="covers"></map></view>
			<view class="index">
				评论
				<view class="item" v-for="(item,index) in list" :key="item.value">
					<view class="left">
						<image :src=item.user.img  mode="center"></image>
					</view>
					<view class="right">
						<view class="title">
							<text style="color: #416699;">{{item.user.name}}</text>
							<text>{{item.estimate.num}}分</text>
						</view>
						<view class="title" style="color: #666666;">
							{{item.estimate.content}}
						</view>
						<view class="time" style="color: #ccc;" >
							{{$u.timeFrom(item.estimate.createTime, 'yyyy年mm月dd日')}}	
						</view>
					</view>
				</view>
				<view class="item">
					
				</view>
				<view class="item">
					
				</view>
			</view>
			<view class="but">
				<view class="butt" 	@click="goTo">
					到这里
				</view>
				<view class="butt" 	@click="goBook">
					立即预订
				</view>
			</view>
		</view>
	</view>
</template>

<script>
export default {
	data() {
		return {
			latitude: 40.861737,
			longitude: 111.831976,
			covers: [
				{
					id:0,
					latitude: 40.861737,
					longitude: 111.831976,
					iconPath: '../../static/image/back.jpg',
					width:40,
					height:40
				}
			],
			hotel:'',
			list:''
		};
	},
	methods:{
		goTo(){
			console.log("111")
			var that = this
			        uni.openLocation({
					latitude: Number(that.latitude) ,
					longitude: Number(that.longitude),
			            success: function () {
			                console.log('success');
			            }
			        });
		},
		goBook(){
			var that =this
			uni.navigateTo({
			    url: '../info-book/info-book?id='+that.hotel.id
			});
		}
	},
	onLoad(e) {
		console.log(e)
		var that = this;
		uni.getStorage({
			key: 'hotelList',
			success: function(res) {
				console.log(e)
				that.hotel = res.data[e.id];
				that.covers[0].latitude=res.data[e.id].lat;
				that.covers[0].longitude=res.data[e.id].lng;
				that.covers[0].iconPath="http://localhost:8080//image/"+res.data[e.id].img;
				that.latitude=res.data[e.id].lat;
				that.longitude=res.data[e.id].lng;
				
				uni.request({
				                url: 'http://localhost:8080//estimate/searchEstimate', 
				                data: {
				            id: that.hotel.id
				        },
						method:"POST",
				        success: (res) => {
				            that.list=res.data
				        }
				});
				
			}
		});
	}
};
</script>

<style lang="scss">
title {
	font-size: 50upx;
	font-weight: bold;
	margin-bottom: 30upx;
}
.back {
	width: 100%;
	height: 60vh;
	position: fixed;
	top: 0;
	z-index: -1;
	image {
		z-index: -1;
		width: 100%;
		height: 100%;
	}
}
.main {
	width: 100%;
	// height: 60vh;
	// position: fixed;
	margin-top: 40vh;
	bottom: 0;
	background-color: #ffffff;
	z-index: 2;
	border-radius: 35upx;
	padding: 50upx;
}
.num {
	width: 100%;
	height: 120upx;
	line-height: 120upx;
	display: flex;
	align-items: center;
	font-size: 36upx;
	image {
		height: 50upx;
		width: 50upx;
		margin-right: 10upx;
	}
}
.text {
	margin-bottom: 30upx;
}
.map {
	map {
		width: 100%;
		height: 300upx;
		border-radius: 35upx;
		overflow: hidden;
		margin-bottom: 30upx;
	}
}
.but{
	position: fixed;
	bottom: 20upx;
	width: 85%;
	height: 150upx;
	display: flex;
	justify-content: space-between;
	.butt{
		width: 48%;
		height: 120upx;
		border-radius: 35upx;
		background-color: #f0f0f0;
		line-height: 120upx;
		text-align: center;
		font-size: 32upx;
		font-weight: bold;
	}
}
.index{
	padding-bottom: 250upx;
	font-size: 50upx;
	font-weight: bold;
	.item{
		width: 100%;
		margin: 20upx 0;
		display: flex;
		.left{
			width: 100upx;
			height: 100%;
			margin-right: 20upx;
			image{
				width: 100upx;
				height: 100upx;
				border-radius: 50%;
				overflow: hidden;
			}
		}
		.right{
			width: calc(100% - 120upx);
			.title{
				font-size: 32upx;
				font-weight: normal;
				margin-bottom: 20upx;
				display: flex;
				justify-content:space-between;
		
			}
			.time{
				font-size: 28upx;
				font-weight: normal;
				margin-bottom: 20upx;
				display: flex;
				justify-content:space-between;
			}
		}
	}
}
</style>
