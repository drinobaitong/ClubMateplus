<template>
    <div class="common-layout">
    <!---侧边栏--->
    <el-container>
      <el-aside class="aside" >
        <!---切换--->
        <el-menu
            background-color="#545c64"
            class="el-menu-vertical-demo"
            text-color="#fff"
        >
          <!---头像--->
          <el-row class="demo-avatar demo-basic">
            <el-col :span="20">
              <div class="demo-basic--circle">
                <div class="block">
                  <el-avatar :size="50" :src="circleUrl" />
                </div>
                <div class="sub-title">admin</div>
              </div>
            </el-col>
          </el-row>
          <div class="menu-list">
            <router-link to="/Chart">
            <el-menu-item index="1">
              <el-icon><icon-menu /></el-icon>
              <span >首页</span>
            </el-menu-item>
              </router-link>
            <router-link to="/ClubReview">
              <el-menu-item index="2" >
                <el-icon><document /></el-icon>
                <span>社团审核</span>
              </el-menu-item>
            </router-link>
            <router-link to="/Cancel">
              <el-menu-item index="3">
                <el-icon><setting /></el-icon>
                <span>社团注销</span>
              </el-menu-item>
            </router-link>
            <router-link to="/ChangeN">
              <el-menu-item index="4">
                <el-icon><setting /></el-icon>
                <span>社团信息变更</span>
              </el-menu-item>
            </router-link>
            <router-link to="/ClubControl">
              <el-menu-item index="5">
                <el-icon><setting /></el-icon>
                <span>社团信息管理</span>
              </el-menu-item>
            </router-link>
            <router-link to="/NumberR">
              <el-menu-item index="5">
                <el-icon><setting /></el-icon>
                <span>成员审核</span>
              </el-menu-item>
            </router-link>
            <router-link to="/PostR">
              <el-menu-item index="6">
                <el-icon><setting /></el-icon>
                <span>社团发帖审核</span>
              </el-menu-item>
            </router-link>
            <router-link to="/ContentControl">
              <el-menu-item index="8">
                <el-icon><setting /></el-icon>
                <span>社团评论管理</span>
              </el-menu-item>
            </router-link>
            <router-link to="/TypeControl">
              <el-menu-item index="9">
                <el-icon><setting /></el-icon>
                <span>社团类型管理</span>
              </el-menu-item>
            </router-link>
          </div>
        </el-menu>
      </el-aside>
      <!---上边栏--->
      <el-container >
        <el-header class="header">
              <span class="text-large font-600 mr-3"><h2>信息总览</h2></span>
        </el-header>
        <el-main style="margin-left: -300px;">
    <div class="circleChart">
        <div id="myChart1" class="chartItem"></div>
        <div id="myChart2" class="chartItem"></div>
    </div>

    <div class="rectChart">
        <div id="myChart3" class="chartItem"></div>
        <div id="myChart4" class="chartItem"></div>
    </div>

    <div class="rectIntroduction">
        <p>新增的社团数</p>
        <p style = "margin-left: 550px;">新加入的成员数</p>
    </div>

</el-main>
      </el-container>
    </el-container>
  </div>
  </template>
  
  <script>
  import {defineComponent, toRefs, reactive, onMounted} from 'vue'
  import * as echarts from 'echarts'
  
  export default defineComponent({
    name: 'Histogram',
    setup() {
      const state = reactive({
        option1: {
          grid: {
            top: '20%',
            left: '53%',
            right: '10%',
            bottom: '6%',
            containLabel: true
          },
          series: [{
            label: {
              normal: {
                formatter: '{b|{b}：} {c} \n  {per|{d}%} ',
                rich: {
                }
              },
              emphasis: {
                show: true,
              }
            },
            // labelLine: {
            //     normal: {
            //         show: false
            //     }
            // },
            name: '访问来源',
            type: 'pie',
            radius: '55%',
            center: ['50%', '60%'],
            data:[{
              value: 8,
              name: '弘毅学堂'
            },
              {
                value: 12,
                name: '文学院'
              },
              {
                value: 5,
                name: '测绘学院'
              },
              {
                value: 6,
                name: '计算机学院'
              },
            ],
            itemStyle: {
              emphasis: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: 'rgba(0, 0, 0, 0.5)'
              }
            },
            labelLine: {
              normal: {
                length: 5,
                length2: 1,
                smooth: true,
              }
            },
          }],
        },
  
        option2: {
          grid: {
            top: '20%',
            left: '53%',
            right: '10%',
            bottom: '6%',
            containLabel: true
          },
          series: [{
            label: {
              normal: {
                formatter: '{b|{b}：} {c} \n  {per|{d}%} ',
                rich: {
                }
              },
              emphasis: {
                show: true,
              }
            },
            // labelLine: {
            //     normal: {
            //         show: false
            //     }
            // },
            name: '访问来源',
            type: 'pie',
            radius: '55%',
            center: ['50%', '60%'],
            data:[{
              value: 1,
              name: '思想政治类'
            },
              {
                value: 2,
                name: '学术科技类'
              },
              {
                value: 17,
                name: '文化体育类'
              },
              {
                value: 5,
                name: '创新创业类'
              },
              {
                value: 5,
                name: '志愿公益类'
              },
              {
                value: 1,
                name: '自律互助类'
              }
            ],
            itemStyle: {
              emphasis: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: 'rgba(0, 0, 0, 0.5)'
              }
            },
            labelLine: {
              normal: {
                length: 5,
                length2: 1,
                smooth: true,
              }
            },
          }],
        },
  
        option3: {
          grid: {
            top: '10%',
            right: '3%',
            bottom: '15%',
            left: '3%',
            containLabel: true
          },
          xAxis: {
            data:  ['1月', '2月', '3月', '4月', '5月', '6月'],
            axisLabel: {
            fontSize:13,
            padding:[10,0,0,0],
            color: 'rgba(0, 0, 0, 0.45)'
          },
          axisTick: {
            show: false
          },
          axisLine: {
            lineStyle: {
            color: 'rgba(0, 0, 0, 0.35)',
            width: 1
            }
          }
        },
        yAxis: [
          {
          type: 'value',
          axisLabel: {
            color: 'rgba(0, 0, 0, 0.45)'
            },
          axisTick: {
            show: false
            },
          axisLine: {
            show: false
            },
          splitLine: {
            lineStyle: {
              type:'dashed',
              color: 'rgba(0, 0, 0, 0.15)'
              }
            }
          },
          {
          type: 'value',
          alignTicks: true,
          splitLine: {
            show: false
          },
          axisLabel: {
            color: 'rgba(0, 0, 0, 0.45)'
            }
          }
        ],
          series: [
        {
          name: 'bar',
          yAxisIndex: 0,
          type: 'bar',
          data: [2, 3, 5, 1, 3, 6].map(val => {
            return {
              value: val,
              label: {
                position: val < 0 ? 'bottom' : 'top',
              },
              itemStyle: {
                borderRadius: val < 0 ? [0, 0, 20, 20] : [20, 20, 0, 0]
              }
            }
          }),
          barMaxWidth: 15,
          itemStyle: {
            color: {
              type: 'linear',
              x: 0,
              y: -0.2,
              x2: 0,
              y2: 1,
              colorStops: [
                {
                  offset: 1,
                  color: '#A7CEFF'
                },
                {
                  offset: 0,
                  color: '#5B8FF9'
                }
              ]
            }
          },
          label: {
            show: true,
            color: "#5B8FF9",
            padding:[5,5,5,5]
          },
        },
        {
          data:  [2, 3, 5, 1, 3, 6],
          type: "line",
          smooth: true,
          symbol: 'circle',
          symbolSize: 10,
          lineStyle: {
            width: 4,
            color: "rgb(55, 200, 143)",
            shadowColor: 'rgba(55, 200, 143, .4)',
            shadowBlur: 8,
            shadowOffsetY: 10,
            shadowOffsetX: 0,
          },
          itemStyle: {
            color: "rgb(55, 200, 143)",
            borderColor: "#fff",
            borderWidth: 2,
            shadowBlur: 5,
            shadowColor: "rgba(5, 5, 5, .3)"
          },
          areaStyle: {
            color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
              offset: 0,
              color: 'rgba(55, 200, 143, .4)'
            }, {
              offset: 1,
              color: 'rgba(55, 200, 143, 0)'
            }]),
  
          },
        }
      ]
        },
  
        option4: {
          grid: {
            top: '10%',
            right: '3%',
            bottom: '15%',
            left: '3%',
            containLabel: true
          },
          xAxis: {
            data:  ['1月', '2月', '3月', '4月', '5月', '6月'],
            axisLabel: {
            fontSize:13,
            padding:[10,0,0,0],
            color: 'rgba(0, 0, 0, 0.45)'
          },
          axisTick: {
            show: false
          },
          axisLine: {
            lineStyle: {
            color: 'rgba(0, 0, 0, 0.35)',
            width: 1
            }
          }
        },
        yAxis: [
          {
          type: 'value',
          axisLabel: {
            color: 'rgba(0, 0, 0, 0.45)'
            },
          axisTick: {
            show: false
            },
          axisLine: {
            show: false
            },
          splitLine: {
            lineStyle: {
              type:'dashed',
              color: 'rgba(0, 0, 0, 0.15)'
              }
            }
          },
          {
          type: 'value',
          alignTicks: true,
          splitLine: {
            show: false
          },
          axisLabel: {
            color: 'rgba(0, 0, 0, 0.45)'
            }
          }
        ],
          series: [
        {
          name: 'bar',
          yAxisIndex: 0,
          type: 'bar',
          data: [5, 7, 10, 12, 10, 11].map(val => {
            return {
              value: val,
              label: {
                position: val < 0 ? 'bottom' : 'top',
              },
              itemStyle: {
                borderRadius: val < 0 ? [0, 0, 20, 20] : [20, 20, 0, 0]
              }
            }
          }),
          barMaxWidth: 15,
          itemStyle: {
            color: {
              type: 'linear',
              x: 0,
              y: -0.2,
              x2: 0,
              y2: 1,
              colorStops: [
                {
                  offset: 1,
                  color: '#A7CEFF'
                },
                {
                  offset: 0,
                  color: '#5B8FF9'
                }
              ]
            }
          },
          label: {
            show: true,
            color: "#5B8FF9",
            padding:[5,5,5,5]
          },
        },
        {
          data:  [5, 7, 10, 12, 10, 11],
          type: "line",
          smooth: true,
          symbol: 'circle',
          symbolSize: 10,
          lineStyle: {
            width: 4,
            color: "rgb(55, 200, 143)",
            shadowColor: 'rgba(55, 200, 143, .4)',
            shadowBlur: 8,
            shadowOffsetY: 10,
            shadowOffsetX: 0,
          },
          itemStyle: {
            color: "rgb(55, 200, 143)",
            borderColor: "#fff",
            borderWidth: 2,
            shadowBlur: 5,
            shadowColor: "rgba(5, 5, 5, .3)"
          },
          areaStyle: {
            color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
              offset: 0,
              color: 'rgba(55, 200, 143, .4)'
            }, {
              offset: 1,
              color: 'rgba(55, 200, 143, 0)'
            }]),
  
          },
        }
      ]
        },
      })
      const initCharts = () => {
        let myChart1 = echarts.init(document.getElementById('myChart1'))
        let myChart2 = echarts.init(document.getElementById('myChart2'))
        let myChart3 = echarts.init(document.getElementById('myChart3'))
        let myChart4 = echarts.init(document.getElementById('myChart4'))
  
        // 绘制图表
        myChart1.setOption(state.option1)
        myChart2.setOption(state.option2)
        myChart3.setOption(state.option3)
        myChart4.setOption(state.option4)
      }
  
      onMounted(() => {
        initCharts()
      })
  
      return {
        ...toRefs(state),
      }
    },
  })
  </script>
  

<style scoped>
.demo-basic {
  text-align: center;
}
.demo-basic .sub-title {
  margin-bottom: 10px;
  font-size: 18px;
  color: var(--el-text-color-secondary);
}
.demo-basic .demo-basic--circle{
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-top: 50px;
  margin-bottom: 20px;
}
.demo-basic .block {
  flex: 1;
}
.aside{
  width:200px;
  height:750px;
  background-color:#545c64;
}
.menu-list{
  margin-left: 15px;
}
.header{
  background: rgba(83, 88, 93, 0.15);
  height:80px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 20px;
}
.demo-form-inline .el-input {
  --el-input-width:120px;
}

.demo-form-inline .el-select {
  --el-select-width: 220px;
}
.changePage{
  margin-top: 50px;
  position: absolute;
  right: 50px;
}
.common-layout{
  background: rgba(217, 153, 217, 0.08);
}
.demo-image__error .image-slot {
  font-size: 30px;
}
.demo-image__error .image-slot .el-icon {
  font-size: 30px;
}
.demo-image__error .el-image {
  width: 100%;
  height: 200px;
}

.circleChart{
    margin-top: -120px;
    margin-left: 600px;
    display: flex;
}

.rectChart{
    margin-top: -20px;
    margin-left: 600px;
    display: flex;
}

.chartItem{
    width: 400px;
    height: 400px;
    margin-right: 250px;
}

.rectIntroduction{
    margin-top: -50px;
    display: flex;
    font-size: 20px;
    font-weight: 700;
    color:#74808b;
    margin-left: 720px;
}
</style>