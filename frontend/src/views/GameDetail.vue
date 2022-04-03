<template>
  <div>
    <a-col class="flex-column around">
      <a-col class="flex-row around" style="height: 10vh;width: 100%">
        <a-col flex="2">
          <img :src="detail.image.virtualPath" alt="" style="width: 80%;height: 100%">
        </a-col>
        <a-col flex="2" class="flex-column" style="width:100%;align-items: start">
          <p>Name:{{ detail.name }}</p>
          <p style="width:20vh;word-wrap: break-word; word-break: normal;">
            Introduction:{{ detail.introduction }}
          </p>
        </a-col>
        <a-col flex="2">
          <a-col class="flex-column" style="align-items: center">
            <a-button size="small" style="width: 50%;margin-top: 10px">Add connection</a-button>
            <a-button size="small" style="width: 50%;margin-top: 10px">Community</a-button>
            <a-button size="small" style="width: 50%;margin-top: 10px">Club</a-button>
          </a-col>
        </a-col>
      </a-col>
      <a-col class="flex-row between" style="width: 100%;margin-top: 2vh">
        <a-col class="flex-column" style="width:60%;">
          <a-col class="flex-column" style="border: 1px solid #a19e9e;padding: 10px ">
              <span class="flex-row" style="align-items: baseline;">
                <span>Score : </span>
                <a-rate :value="detail.score/detail.scoreCount" disabled/>
                <h4 style="margin-left: 10px">{{ detail.score / detail.scoreCount }}</h4>
              </span>
            <span class="flex-row" style="align-items: baseline">
                <span>Number of Ratings : </span>
                <span>{{ detail.scoreCount }}</span>
              </span>
          </a-col>
          <a-col class="flex-column" style="border: 1px solid #a19e9e;margin-top:10px">
              <span class="flex-row between" style="align-items: baseline;font-weight: 700;padding: 10px;">
                <span style="">Comments : </span>
                <span><a-icon type="double-right"/>All</span>
              </span>
            <template v-if="detail.gameComments[0]">
              <span class="flex-row between"
                    style="align-items: baseline;font-weight: 700;padding: 10px;border-top: 1px solid #a19e9e;">
                  <span style="">User 1 : </span>
                  <a-tooltip placement="topLeft">
                    <template slot="title">
                      <span>{{ detail.gameComments[0].content }}</span>
                    </template>
                   <span style="width: 40%;white-space:nowrap;overflow:hidden;text-overflow:ellipsis;">
                    {{ detail.gameComments[0].content }}
                  </span>
                  </a-tooltip>
                  <span>{{ detail.gameComments[0].createTime }}</span>
              </span>
            </template>
            <template v-if="detail.gameComments[1]">
              <span class="flex-row between"
                    style="align-items: baseline;font-weight: 700;padding: 10px;border-top: 1px solid #a19e9e;">
                  <span style="">User 2 : </span>
                  <a-tooltip placement="topLeft">
                    <template slot="title">
                      <span>{{ detail.gameComments[1].content }}</span>
                    </template>
                   <span style="width: 40%;white-space:nowrap;overflow:hidden;text-overflow:ellipsis;">
                    {{ detail.gameComments[1].content }}
                  </span>
                  </a-tooltip>
                  <span>{{ detail.gameComments[1].createTime }}</span>
              </span>
            </template>
          </a-col>
          <a-col class="flex-column" style="border: 1px solid #a19e9e;margin-top:10px">
              <span class="flex-row between" style="align-items: baseline;font-weight: 700;padding: 10px;">
                <span style="">Other website Comments : </span>
                <span><a-icon type="double-right"/>All</span>
              </span>
            <span class="flex-row between"
                  style="align-items: baseline;font-weight: 700;padding: 10px;border-top: 1px solid #a19e9e;">
                <span style="">Website 1 : </span>
                <span>xxxxx</span>
                <span>xxxxx</span>
              </span>
            <span class="flex-row between"
                  style="align-items: baseline;font-weight: 700;padding: 10px;border-top: 1px solid #a19e9e;">
                <span style="">Website 2 : </span>
                <span>xxxxx</span>
                <span>xxxxx</span>
              </span>
          </a-col>
        </a-col>
        <a-col class="flex-column" style="width:30%;font-weight: 600">
          <span>Add Ratings : </span>
          <a-rate :value="score" style="width: 100%" @change="changeRate"/>
          <span>Add Comments : </span>
          <a-textarea :rows="6" v-model="content"/>
          <a-col class="flex-row around" style="margin-top: 1vh">
            <a-button style="background-color:#6e6c6c;color: #FFF" size="small">Cancel</a-button>
            <a-button style="background-color:#6e6c6c;color: #FFF" size="small" @click="clickSubmit">Submit</a-button>
          </a-col>
        </a-col>
      </a-col>
    </a-col>
  </div>
</template>
<script>
export default {
  name: 'GameDetail',
  props: {
    saveGameDetail: {
      type: Function,
    },
    detail: {
      type: Object,
      default: () => {
        return {image: {}, gameComments: [{content: '', createTime: ''}, {content: '', createTime: ''}]}
      }
    },
  },
  data() {
    return {
      score: 0,
      content: '',
      image: require('@/assets/image1.png'),
    }
  },
  components: {},
  mounted() {
  },
  methods: {
    clickSubmit() {
      let data = {
        gameId: this.detail.id,
        score: this.score,
        content: this.content,
      }
      this.$emit('saveGameDetail', data)
    },
    changeRate(e) {
      this.score = e
    },
  }
}

</script>
