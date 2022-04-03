<template>
  <div>
    <a-row class="flex-row" style="width: 100%;">
      <a-col :span="6" style="padding: 20px;height: 35vh">
        <div class="flex-column" style="width: 80%;justify-content: center;text-align: center">
          <a-button style="margin-top: 2vh;width: 100%;background-color: rgb(95,99,104);color: #fff">My love
          </a-button>
          <a-button style="margin-top: 2vh;width: 100%">Custom List 1</a-button>
          <a-button style="margin-top: 2vh;width: 100%">Custom List 2</a-button>
        </div>
        <div class="flex-row around" style="margin-top: 2vh;width: 80%;">
          <a-icon type="plus-square" style="font-size: 2em"/>
          <a-icon type="minus-square" style="font-size: 2em"/>
        </div>
      </a-col>
      <a-col :span="14" style="padding:20px;">
        <a-input placeholder="Search collections..." style="width: 50%;margin-top: 10px">
                <span slot="suffix">
                  <a-icon type="audio"/>
                  <a-icon type="search"/>
                </span>
        </a-input>
        <div class="flex-row" style="justify-content: end">
          <a-select default-value="Added Time" style="width: 160px;border: none" :open="false">
            <i slot="suffixIcon" class="iconfont"> &#xe8f7;</i>
            <a-select-option value="Added Time">Added Time</a-select-option>
          </a-select>
        </div>
        <div class="flex-row around" style="width: 100%;flex-wrap: wrap;">
          <div v-for="item in collections" style="width: 30%;text-align: center;margin-top: 2em">
            <img :src="item.image" alt="" style="width: 100%;height: 15vh">
            <p class="flex-row around" style="align-items: center">
              <a-icon type="share-alt" style="font-size: 2em"/>
              <i class="iconfont" style="font-size: 2em;font-weight: 800"
                 @click="moveCollections(item)">&#xe70c;</i>
              <a-icon type="delete" style="font-size: 2em" @click="deleteCollections(item)"/>
            </p>
          </div>
        </div>
        <div class="flex-row" style="margin: 10px auto;justify-content: center">
          <a-pagination :default-current="6" :total="100"/>
        </div>
      </a-col>
    </a-row>
  </div>
</template>
<script>

export default {
  name: 'Collection',
  components: {},
  data() {
    return {
      collections: [
        {id: '1', name: 'Game1', rate: '4.7', image: require('@/assets/img10.png')},
        {id: '2', name: 'Game2', rate: '4.8', image: require('@/assets/img11.png')},
        {id: '3', name: 'Game3', rate: '2.4', image: require('@/assets/img12.png')},
        {id: '4', name: 'Game4', rate: '3.9', image: require('@/assets/img13.png')},
        {id: '5', name: 'Game4', rate: '3.9', image: require('@/assets/img14.png')},
        {id: '6', name: 'Game4', rate: '3.9', image: require('@/assets/img15.png')},
      ],
    }
  },
  mounted() {
  },
  methods: {
    deleteCollections(e) {
      this.$confirm({
        title: 'Delete this item?',
        okText: 'Delete',
        okType: 'danger',
        cancelText: 'Cancel',
        icon: 'none',
        onOk() {
          console.log('OK');
        },
        onCancel() {
          console.log('Cancel');
        },
      });
    },
    moveCollections(e) {
      this.$confirm({
        content: h => {
          return (
              <div>
                <h2>Move this item to:</h2>
                <p>
                  <a-checkbox>Custom List 1</a-checkbox>
                </p>
                <p>
                  <a-checkbox>Custom List 2</a-checkbox>
                </p>
              </div>
          )
        },
        okText: 'Move',
        okType: 'primary',
        cancelText: 'Cancel',
        icon: 'none',
        onOk() {
          console.log('OK');
        },
        onCancel() {
          console.log('Cancel');
        },
      });
    },
  }
}
</script>
