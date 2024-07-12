<script setup>
import { reactive, toRefs } from 'vue'

const state = reactive({
  circleUrl:
      'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png',
})

const { circleUrl } = toRefs(state)

//import type { ImageProps } from 'element-plus'

/*const fits = [
  'fill',
  'contain',
  'cover',
  'none',
  'scale-down',
] as ImageProps['fit'][]*/

/*网页图片
const url =
    'https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg'

 */
</script>

<template>
  <el-container>

    
    <!--第一部分-->
    <el-container>

        <el-aside width="300px" height="400px" margin-bottom="20px">
          <!--justify="center" type="flex" align="middle"使一行中的每列中的内容水平垂直居中-->
          <el-row class="el-row1" justify="center" type="flex" align="middle">
            <el-col :span="12" align="center">
              <div class="userPic">
                <el-avatar :size="60" :src="circleUrl" />
              </div>
            </el-col>
            <el-col :span="12"><!--水平居中可加入 type="flex" align="center"-->
              <div class="postSender" style="font-size: 25px; display: flex; justify-content: center; align-items: center;">
              排球队队长
              </div>
            </el-col>
          </el-row>
          <el-row class="el-row2" justify="center" type="flex" align="middle">
            <el-col :span="24" type="flex" align="center">
              <div class="postTime" style="font-size: 15px;">
              计算机学院排球队 2024-07-02 11:15
              </div>
            </el-col>
          </el-row>
        </el-aside>

      <el-main style="font-size: 35px; display: flex; justify-content: center; align-items: center;">
        排球队春季招新啦！！！！！！零基础友好，快来报名吧！！！！！
      </el-main>

    </el-container>


        <!--第二部分-->
  <el-main>

    <div class="image">

      <div class="block">
        <el-image src="./src/picture/帖子（排球）1.png" fit="contain" />
        <!--网页图片<el-image :src="url" fit="contain" />-->
      </div>

      <div class="text-content">
        <div>
          <p>自2012年漫画连载起航，2014年动画化后广受好评，至2020年漫画连载落幕与最后一季动画播出，《排球少年》在不少观众心中已然成为一部经典之作。尽管通常作品完结后，人气和盈利能力会逐渐下降，但《排球少年》在完结四年后以剧场版《排球少年!! 垃圾场的决战》的形式回归，依旧展现出了不减的活力与魅力。</p>
        </div>
        <div>
          <p>本次剧场版之所以被命名为“垃圾场决战”，背后有着深刻的寓意。宫城县代表队乌野与东京代表队音驹，两队的名称在汉语中分别与“乌鸦”和“猫”谐音，而这两者都是垃圾回收场周围的常客。乌野和音驹的排球队都曾站上过全国大赛，也都经历过低谷期。乌野队曾一度被称为“飞不起来的乌鸦，没落的豪强”，但随着新生力量的注入，两支队伍都逐渐恢复了往日的荣光，并在全国大赛上展开了一场无法重来的精彩对决。</p>
        </div>
        <div>
          <p>《排球少年》剧场版在国内市场取得的优异票房成绩，是值得深思的现象。体育大生意分析认为，首先得益于《排球少年》TV版前四季在豆瓣上高达9.6分的评分，赢得了良好的口碑。其次，该动漫在中国市场早已积累了大量忠实粉丝，他们对角色的热爱和对故事的追随，为剧场版的上映打下了坚实的基础。</p>
        </div>
      </div>

      <div class="block">
        <el-image src="./src/picture/帖子（排球）2.png" fit="contain" />
      </div>

      <div class="text-content">
        <p>体育动漫，作为一种独特的艺术形式，它以体育竞技为背景，通过漫画和动画这两种富有表现力的媒介，向我们展示了体育精神的独特魅力。这些作品通常以生动的故事情节、夸张而富有吸引力的视觉效果，以及对体育项目深入而生动的介绍，为观众带来了比观看实际体育比赛更加丰富多彩的体验。</p>
        <p>在体育动漫的世界里，我们不仅能享受到竞技体育的紧张刺激，还能感受到运动员们在赛场内外所经历的温情与挑战。这种类型的动漫作品，因其能够激发观众的情感共鸣和对体育的热爱，而在日本这个动漫大国中尤为盛行。</p>
        <p>以足球为例，日本动漫界创作了众多以足球为核心题材的杰出作品，如《闪电十一人》《足球小将》《青之芦苇》以及《蓝色监狱》等。在这些作品中，最为人所熟知的无疑是1981年首次与全球观众见面的《足球小将》。这部作品不仅在日本国内产生了深远的影响，也在国际上赢得了极高的声誉，成为了体育动漫领域的一个标杆。通过这些作品，我们可以看到日本动漫如何以其独特的方式，将体育竞技的激情与艺术创作完美融合，为全球观众带来了无尽的灵感和动力。</p>
      </div>

      <div class="block">
        <el-image src="./src/picture/帖子（排球）3.png" fit="contain" />
      </div>

      <div class="text-content">
        <p>无论是日本的足球巨星中田英寿、本田圭佑，还是意大利的"罗马王子"弗朗西斯科·托蒂、西班牙的马德里竞技前锋费尔南多·托雷斯，以及德国的足球名将卢卡斯·波多尔斯基，他们都曾在不同场合公开表示，是《足球小将》中的主角大空翼激发了他们对足球的热爱，并引领他们走上了职业足球的道路。</p>
        <p>在日本，这种由动漫作品所激发的足球热情对足球运动的发展产生了深远的影响。据统计，自《足球小将》1981年开始连载以来，日本足球人口实现了显著增长，小学生足球注册人数从11万人激增至1988年连载结束时的24万人。</p>
        <p>而在2022年卡塔尔世界杯上，日本国家队的表现更是令人瞩目。他们先后战胜了德国和西班牙两支传统足球强国，以小组第一的身份晋级16强，实现了漫画中的梦想。这不仅是对《足球小将》精神的最好诠释，也是对日本足球多年来不懈努力和持续发展的最好证明。</p>
      </div>

      <div class="block">
        <el-image src="./src/picture/帖子（排球）4.png" fit="contain" />
      </div>

      <div class="text-content">
        <p>除了足球和排球外，近年来，尽管日本动漫界推出了如《黑子的篮球》和《鸭子的天空》等以篮球为主题的作品，它们虽然各自拥有一定的粉丝基础，但在影响力和受欢迎程度上，似乎仍难以与《灌篮高手》相提并论。这部经典之作在日本篮球文化的推广上发挥了不可替代的作用，极大地提高了篮球运动在日本的普及率和受欢迎程度。</p>
        <p>日本篮球协会青少年和技术发展部高级经理山本明也对《灌篮高手》称赞有加，并表示，《灌篮高手》在日本社会产生了巨大而深远的影响，不仅感染了广大青少年，还让很多成年人对篮球感兴趣。</p>
        <p>此外，漫画的广泛传播，也促进了日本篮球人口的显著增长。到了1996年，也就是《灌篮高手》连载结束的那一年，日本的篮球竞技参与者数量超过了100万人，创下了历史新高。这一成就不仅凸显了《灌篮高手》在篮球推广方面的重要作用，也反映了其在塑造篮球文化方面的深远影响。</p>
      </div>

      <div class="block">
        <el-image src="./src/picture/帖子（排球）5.png" fit="contain" />
      </div>

      <div class="text-content">
        <p>在2024年，日本三大球类运动项目共有六支队伍成功跻身巴黎奥运会，这一令人瞩目的成绩背后，是日本选手们与生俱来的热血与激情——一种被称为"中二魂"的精神。他们渴望成为动漫作品中的英雄人物，并为此不懈努力。在动漫中，主角们往往不仅拥有超凡的体育天赋，更具备坚定的意志和决心，这些鲜活的形象成为了观众心中的偶像，同时也为现实中的运动员们树立了榜样，激励着他们追求卓越，勇攀高峰。</p>
        <p>体育动漫与体育教育的结合，为青少年提供了一种新颖且富有吸引力的学习途径。在轻松愉快的氛围中，青少年能够通过观看体育动漫学习体育知识，培养对体育的兴趣和热情，提高自身的体育素养。无论是《排球少年》《灌篮高手》还是近年来备受欢迎的《强风吹拂》，这些作品中的主角们都是积极参与学校体育社团活动的学生们，他们通过不懈的努力，最终踏上了职业赛场，为国家赢得荣誉。</p>
      </div>
    </div>
  </el-main>


  <!--第三部分-->
  <el-footer>

    <router-link to="/MePost">
    <el-button type="info" round>写留言</el-button>
    </router-link>


    <el-row class="message-row">
      <!-- 头像列 -->
      <el-col :span="1" class="avatar-column">
        <div class="commentPic">
          <el-avatar :size="40" :src="circleUrl" />
        </div>
      </el-col>

      <!-- 用户名列 -->
      <el-col :span="3" class="username-column">
        <div class="commentName">
          KID
        </div>
      </el-col>

      <!-- 时间列 -->
      <el-col :span="4" class="time-column">
        <div class="commentTime">
          2024-07-02 21:15 一楼
        </div>
      </el-col>
    </el-row>

    <el-row>
      <el-col :span="22" :offset="1">
        <div class="comment">
        好燃！一直觉得打排球好帅！我要加入！
        </div>
      </el-col>
    </el-row>


    <el-row>
      <!-- 头像列 -->
      <el-col :span="1" class="avatar-column">
        <div class="commentPic">
          <el-avatar :size="40" :src="circleUrl" />
        </div>
      </el-col>

      <!-- 用户名列 -->
      <el-col :span="3" class="username-column">
        <div class="commentName">
          Akai Hailey.SR
        </div>
      </el-col>

      <!-- 时间列 -->
      <el-col :span="4" class="time-column">
        <div class="commentTime">
          2024-07-02 22:07 二楼
        </div>
      </el-col>
    </el-row>

    <el-row>
      <el-col :span="22" :offset="1">
        <div class="comment">
          啊啊啊啊啊啊啊啊啊啊啊好厉害！排人怎么都这么帅啊啊啊啊啊！我要加入！我要加入！看我看我看我！
        </div>
      </el-col>
    </el-row>


  </el-footer>

  </el-container>




</template>

<style scoped>
.el-row1 {
  margin-bottom: 15px;
  display: flex;
  flex-wrap: wrap;
  position: relative; /* 使得内部元素可以基于此定位 */
  line-height: 1.2; /* 调整行高 */
}

.el-row2 {
  margin-bottom: 15px;
  display: flex;
  flex-wrap: wrap;
  line-height: 1.2; /* 调整行高 */
}

.image {
  text-align: center; /* 让内部内容居中对齐 */
}

.image .block {
  display: inline-block; /* 改为行内块级元素 */
  text-align: center; /* 内容居中对齐 */
  margin-bottom: 20px; /* 图片间的垂直间距 */
}

.text-content {
  text-align: left; /* 文字内容左对齐 */
}

.text-content > div {
  margin-bottom: 20px; /* 段落之间的垂直间距 */
}

.avatar-column {
  display: flex;
  align-items: center; /* 垂直居中头像 */
}

.username-column,
.time-column {
  display: flex;
  align-items:center; /* 文字底部对齐 */
  height: 40px; /* 确保列高度与头像列一致，以便对齐 */
}

.message-row {
  margin-top: 15px; /* 调整 el-row 和按钮之间的底部间距 */
}
</style>