  <template>    
   <div id="whole" class="page">
    <div class="box" v-for="(club, index) in paginatedLists" :key="index">    
      <NotLogged :club="club" :setDialogVisible="someDialogVisibilityMethod" :wid="300" :hei="300" />   
      <div style="margin-left: 40px;">    
        <h4 style="font-size: 24px;">{{ club.name }}</h4>    
        <p>{{ club.tags }}</p>    
        <p>{{ club.unit }}</p>    
      </div>    
    </div>    
  </div>    
  <div class="pagination">    
      <el-pagination    
        @current-change="handleCurrentChange"    
        :current-page="currentPage"    
        :page-size="pageSize"    
        layout="prev, pager, next"    
        :total="totalItems"    
        class="mt-4"    
      />    
    </div>    
</template>  

<script setup>    
  import { ref, computed } from 'vue';    
  import NotLogged from './NotLogged.vue';  
  
  // 定义从父组件接收的 props  
  const props = defineProps(['lists']); // 社团列表，注意这里我们直接命名为 props  
  
  // 使用 props.lists 来访问列表  
  const currentPage = ref(1);    
  const pageSize = ref(4); // 每页显示的社团数量    
  const totalItems = computed(() => props.lists.length); 
    
  // 计算当前应该显示的列表项  
  const paginatedLists = computed(() => {    
      const start = (currentPage.value - 1) * pageSize.value;    
      const end = start + pageSize.value;    
      return props.lists.slice(start, end); 
    });    
    
  // 处理页码变化  
  const handleCurrentChange = (newPage) => {    
      currentPage.value = newPage;    
    };    
</script>  
  
<style scoped>    

.page{
  display:flex;
  /* justify-content:space-evenly; */
  flex-wrap:wrap;
}

.box{
  margin-left:120px;
  display:flex;
  width:500px;
  height:300px;
  margin-top:35px;
  margin-bottom:30px;
} 

.pagination{
    margin-top: 20px;
    margin-left: 570px;
  }
</style>