<template>
  <div>
<!--    <el-card style="width: 100%;height: 550px">-->
      <v-chart
        v-if="chartReady"
        :options="chartOptions"
        autoresize
        style="width: 100%;height: 550px"
        manual-update
        ref="chart"></v-chart>
<!--    </el-card>-->
  </div>
</template>

<script>
import {mapState, mapActions} from 'vuex'
// import echarts from 'echarts'

export default {
  name: 'chartData',
  data () {
    // eslint-disable-next-line no-return-assign
    return {
      chartReady: false,
      chartOptions: {
        backgroundColor: '#45515a', // 背景颜色
        // 标题
        title: {
          text: '慕颜物业管理系统用户数量统计',
          left: 'center',
          top: 20,
          textStyle: {
            color: '#ccc',
            fontStyle: 'italic' // 标题字体
          }
        },
        // 弹窗，响应鼠标指向，显示具体细节
        tooltip: {
          trigger: 'item', // 以具体项目触发弹窗
          formatter: '{a} <br/>{b} : {c} ({d}%)'
        },
        // 图例，选择要显示的项目
        legend: {
          orient: 'vertical',
          left: 'left',
          textStyle: {
            color: '#c8c8d0'
          }
          // data: [] // 注意要和数据的name相对应
        },
        // 工具箱
        toolbox: {
          show: true, // 显示工具箱
          feature: {
            dataView: {show: true}, // 以文字形式显示数据
            restore: {show: true}, // 还原
            // dataZoom:{show:true}, //区域缩放
            saveAsImage: {show: true} // 保存图片
            // magicType:{type:['line','bar']}//动态数据切换，数据显示可以在该规定内容中切换显示方式，
          }
        },
        series: [
          {
            name: '用户数量',
            type: 'pie',
            radius: '50%',
            // avoidLabelOverlap: false,
            emphasis: {
              itemStyle: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: 'rgba(0, 0, 0, 0.5)'
              }
            },
            data: [],
            // roseType: 'radius'// 角度和半径展现百分比，'area'只用半径展现
            label: { // 饼图图形的文本标签
              normal: { // 下同，normal指在普通情况下样式，而非高亮时样式
                textStyle: {
                  color: 'rgba(255, 255, 255, 0.5)'
                }
              }
            },
            labelLine: { // 引导线样式
              normal: {
                lineStyle: {
                  color: 'rgba(255, 255, 255, 0.3)'
                },
                smooth: 0.5, // 0-1，越大越平滑弯曲
                length: 10, // 从块到文字的第一段长
                length2: 20 // 拐弯到文字的段长
              }
            },
            itemStyle: { // 图例样式
              normal: {
                // color: '#97413c',
                shadowBlur: 50, // 阴影模糊程度
                shadowColor: 'rgba(0, 0, 0, 0.5)'// 阴影颜色，一般黑
              }
            },
            animationType: 'scale', // 初始动画效果，scale是缩放，expansion是展开
            animationEasing: 'elasticOut', // 初始动画缓动效果
            animationDelay: function (idx) { // 数据更新动画时长，idx限定了每个数据块从无到有的速度
              return Math.random() * 200
            }
          }
        ]
      }
    }
  },
  // 计算属性 监听vuex中的数据
  computed: {
    ...mapState({
      chartData: state => state.echarts.chartData
    })
  },
  methods: {
    // 动态获取饼状图的数据
    async ChartData () {
      await this.loadChartData() // 等待数据加载完成
      this.chartReady = true // 数据加载完成后，设置 chartReady 为 true
    },
    ...mapActions({
      'loadChartData': 'echarts/loadChartData'
    })
  },
  mounted () {
  // 在组件挂载时调用新的 loadChartData action
    this.ChartData()
    this.$refs.chart && this.$refs.chart.$on('ready', () => {
      this.chartReady = true
    })
    // this.chartOptions.series[0].data = this.chartData // 更新饼图数据
  },
  watch: {
    // 当 chartData 更新时，重新渲染图表
    chartData: {
      immediate: true, // 立即执行一次
      handler (newVal) {
        if (newVal && newVal.length > 0) {
          this.chartOptions.series[0].data = newVal
          this.chartOptions.legend.data = newVal.map(item => item.name)
          this.$nextTick(() => {
            if (this.$refs.chart) {
              this.$refs.chart.mergeOptions(this.chartOptions) // 更新图表
            }
          })
          // this.chartReady = true
          // this.$refs.chart.mergeOptions(this.chartOptions) // 更新图表
        }
      }
    }
  }
}
</script>
