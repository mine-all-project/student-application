<template>
  <div>
    <a-row class="flex-row between" style="width: 100%;align-items: center">
      <a-col :span="18" style=";padding: 20px;">
        <a-col>
          <a-input placeholder="Search community..." style="width: 30%">
                <span slot="suffix">
                  <a-icon type="audio"/>
                  <a-icon type="search"/>
                </span>
          </a-input>
        </a-col>
        <a-col class="border" style="margin-top: 10px">
          <p class="flex-row between" style="font-size: 26px;height: 20%;padding: 5px 10%">
            <span>Hot Posts</span>
            <span>All  <a-icon type="right"/></span>
          </p>
          <a-row class="flex-column around" style="height: 45vh;align-items: center">
            <a-col v-for="item in games1" style="width: 80%;">
              <a-col class="flex-row around" style="width: 100%;border: 1px solid #f1f1f1;align-items: center;">
                <a-col style="width: 10%;text-align: right">
                  <a-avatar icon="user"/>
                  <p>{{ item.userName }}</p>
                </a-col>
                <a-col style="width: 70%;padding: 10px 0">
                  <img :src="item.image" alt="" style="width: 100%;height: 10vh">
                </a-col>
              </a-col>
              <a-col class="flex-row between"
                     style="width: 100%;align-items: center;padding: 0 10%;margin-top: 5px">
                <div style="flex: 3">
                  <a href="javascript:" style="text-decoration:underline;" @click="clickDetail(item)">
                    {{ item.gameName }}
                  </a>
                </div>
                <div style="flex: 1;display: flex;justify-content: space-around;font-size: 1.5em">
                  <a-icon type="message" @click="clickCommunityComment(item)"/>
                  <a-icon type="like"/>
                </div>
              </a-col>
            </a-col>
          </a-row>
        </a-col>
      </a-col>
      <a-col :span="4" class="border" style="padding:20px;">
        <p class="flex-row around" style="font-size: 26px;height: 20%;align-items: center">
          <span>Latest News</span>
        </p>
        <p class="flex-row " style="font-size: 22px;height: 20%;align-items: end;justify-content: end">
          <span>All  <a-icon type="right"/></span>
        </p>
        <a-row class="flex-column around" style="height: 45vh;align-items: center">
          <a-col v-for="item in games2" style="width: 100%;text-align: center;padding: 5px;">
            <img :src="item.image" alt="" style="width: 80%;max-height: 10vh">
          </a-col>
        </a-row>
      </a-col>
    </a-row>
  </div>
</template>
<script>

export default {
  name: 'Community',
  props: {
    showDetail: {
      type: Function,
    },
    showCommunityComment: {
      type: Function,
    }
  },
  data() {
    return {
      games1: [
        {id: '1', gameName: 'Game 1', userName: 'user 1', image: require('@/assets/image1.png')},
        {id: '2', gameName: 'Game 2', userName: 'user 2', image: require('@/assets/image1.png')},
        {id: '3', gameName: 'Game 3', userName: 'user 3', image: require('@/assets/image1.png')},
      ],
      games2: [
        {id: '1', image: require('@/assets/image1.png')},
        {id: '2', image: require('@/assets/image1.png')},
        {id: '3', image: require('@/assets/image1.png')},
      ],
    }
  },
  mounted() {
  },
  methods: {
    clickDetail(e) {
      this.$emit('showDetail', e)
    },
    clickCommunityComment(e) {
      this.$emit('showCommunityComment', e)
    },
  }
}
</script>
