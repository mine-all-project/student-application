<template>
  <div>
    <a-col class="flex-column around">
      <a-col class="flex-row around" style="height: 18vh">
        <a-col flex="2">
          <img :src="image" alt="" style="width: 80%;height: 100%">
        </a-col>
        <a-col flex="2" class="flex-column" style="height: 100%;align-items: center">
          <table style="height: 100%">
            <tr>
              <td style="font-weight: 700;font-size: 1.5em">Club 1</td>
            </tr>
            <tr v-for="item in clubDetail">
              <td>{{ item.value }}</td>
            </tr>
          </table>
        </a-col>
        <a-col flex="3" style="height: 100%">
          <table style="height: 100%" class="openTimeTable">
            <tr>
              <td rowspan="8">Open Time :</td>
            </tr>
            <tr v-for="item in clubOpenTime">
              <td class="openTimeTd">{{ item.value }}</td>
            </tr>
          </table>
        </a-col>
        <a-col flex="2">
          <a-col class="flex-row" style="justify-content: start;align-items: baseline">
            <h4>Interested? Join!</h4>
            <a-button size="small" style="margin-left: 10px;padding: 0 20px">Join</a-button>
          </a-col>
          <a-col class="flex-column" style="margin-top: 20px">
            <p>Games:</p>
            <p>Game1</p>
            <p>Game2</p>
          </a-col>
        </a-col>
      </a-col>
      <a-col class="flex-column" style="width: 100%;border: 1px solid #a19e9e;height:36vh;margin-top: 1vh">
        <a-col style="border-bottom: 1px solid #a19e9e;height: 12vh;">
          <H3>Description</H3>
          <a-col style="margin:0 auto;width: 80%;height: 8vh;border: 1px solid #a19e9e;"></a-col>
        </a-col>
        <a-col style="border-bottom: 1px solid #a19e9e;height: 12vh;">
          <H3>Activity</H3>
          <a-col style="margin:0 auto;width: 80%;height: 8vh;border: 1px solid #a19e9e;"></a-col>
        </a-col>
        <a-col style="height: 12vh;">
          <H3>Find us</H3>
          <a-col style="margin:0 auto;width: 80%;height: 8vh;border: 1px solid #a19e9e;"></a-col>
        </a-col>
      </a-col>
    </a-col>
  </div>
</template>
<script>
export default {
  name: 'ClubDetail',
  props: {},
  data() {
    return {
      image: require('@/assets/image1.png'),
      clubDetail: [
        {id: "1", value: 'Administrator : John White'},
        {id: "2", value: 'Members : 1.1K'},
        {id: "3", value: 'Membership Fee : £8'},
      ],
      clubOpenTime: [
        {id: "1", value: 'Monday : 6pm-10pm'},
        {id: "2", value: 'Tuesday : 6pm-10pm'},
        {id: "3", value: 'Wednesday : 6pm-10pm'},
        {id: "4", value: 'Thursday : 6pm-10pm'},
        {id: "5", value: 'Friday : 6pm-10pm'},
        {id: "6", value: 'Saturday : 9pm-10pm'},
        {id: "7", value: 'Sunday : 9pm-10pm'},
      ],
    }
  },
  components: {},
  mounted() {
  },
  methods: {}
}

</script>
