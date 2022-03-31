<template>
 <div>
   <a-row class="flex-row between" style="width: 100%;align-items: center">
     <a-col :span="18" class="border" style=";padding: 20px;">
       <p class="flex-row between" style="font-size: 26px;height: 20%">
         <span>Game Recommendation</span>
         <span>All  <a-icon type="right"/></span>
       </p>
       <a-row class="flex-row around" style="height: 50vh">
         <a-col v-for="item in games" style="width: 100%;height:100%;text-align: center">
           <img :src="item.image" alt="" style="width: 80%;height: 32vh">
           <p style="font-size: 26px">{{ item.name }} : {{ item.rate }}/5.0 </p>
           <a-button>Add in Collection</a-button>
         </a-col>
       </a-row>
     </a-col>
     <a-col :span="4" class="border" style="padding:20px;">
       <p class="flex-row around" style="font-size: 26px;height: 20%;align-items: center">
         <span>Latest News</span>
       </p>
       <a-row class="flex-column around" style="height: 50vh;align-items: center">
         <a-col v-for="item in games" style="width: 100%;text-align: center;padding: 5px;">
           <img :src="item.img" alt="" style="width: 80%;max-height: 10vh">
         </a-col>
       </a-row>
     </a-col>
   </a-row>
 </div>
</template>

<script>
export default {
  name: 'Home',
  components: {},
  data() {
    return {
      games: [
        {id: '1', name: 'Game1', rate: '4.7', image: require('@/assets/image1.png')},
        {id: '2', name: 'Game2', rate: '4.8', image: require('@/assets/image1.png')},
        {id: '3', name: 'Game3', rate: '2.4', image: require('@/assets/image1.png')},
        {id: '4', name: 'Game4', rate: '3.9', image: require('@/assets/image1.png')},
      ],
    }
  },
  mounted() {
  },
  methods: {
  }
}
</script>
<style>
</style>
