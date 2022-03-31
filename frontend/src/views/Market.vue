<template>
  <div>
    <div class="flex-row between">
      <a-input style="width: 50vh;">
                <span slot="suffix">
                  <a-icon type="audio"/>
                  <a-icon type="search"/>
                </span>
      </a-input>
      <a-button> filter</a-button>
    </div>
    <div class="flex-column between" style="margin-top: 2vh;">
      <div class="flex-column border" v-for="item in markets"
           style="width: 100%;padding:10px 0 ;justify-content: start;">
        <div class="flex-row around" style="width: 100%;align-items: center">
          <img :src="item.image" alt="" style="height: 10vh">
          <div style="width: 70%">
            <p>Description: {{ item.description }}</p>
            <p>Price: {{ item.price }}</p>
            <p>From Website: {{ item.fromWebsite }}</p>
          </div>
          <div>
            <a-button>View</a-button>
          </div>
        </div>
        <div style="margin-left: 5vh;">{{ item.name }}</div>
      </div>
    </div>
    <div class="flex-row" style="margin: 10px auto;justify-content: center">
      <a-pagination :default-current="6" :total="500"/>
    </div>
  </div>
</template>
<script>

export default {
  name: 'Home',
  components: {},
  data() {
    return {
      markets: [
        {
          id: '1',
          name: 'xxxxxxxxxxxx',
          description: 'xxxxxxxxxxxx',
          price: '4.7',
          fromWebsite: 'xxxxxxxxxxxxx',
          image: require('@/assets/image1.png')
        },
        {
          id: '2',
          name: 'xxxxxxxxxxxx',
          description: 'xxxxxxxxxxxx',
          price: '4.7',
          fromWebsite: 'xxxxxxxxxxxxx',
          image: require('@/assets/image1.png')
        },
        {
          id: '3',
          name: 'xxxxxxxxxxxx',
          description: 'xxxxxxxxxxxx',
          price: '4.7',
          fromWebsite: 'xxxxxxxxxxxxx',
          image: require('@/assets/image1.png')
        },
        {
          id: '4',
          name: 'xxxxxxxxxxxx',
          description: 'xxxxxxxxxxxx',
          price: '4.7',
          fromWebsite: 'xxxxxxxxxxxxx',
          image: require('@/assets/image1.png')
        },
      ],
    }
  },
  mounted() {
  },
  methods: {
    changeTab(e) {
      console.log(e)
      this.activeTab = e
    },
  }
}
</script>
