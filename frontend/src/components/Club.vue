<template>
  <div>
    <a-row class="flex-row between" style="width: 100%;">
      <a-col :span="6" style="padding: 20px;height: 35vh">
        <h1>Find a club</h1>
        <div class="flex-row between" style="align-items: center">
          <h3> Country: </h3>
          <a-select default-value="United Kingdom" style="width: 140px" size="small ">
            <a-select-option value="United Kingdom">United Kingdom</a-select-option>
            <a-select-option value="United States">United States</a-select-option>
            <a-select-option value="French">French</a-select-option>
          </a-select>
        </div>
        <div class="flex-row between" style="align-items: center;margin-top: 10px">
          <h3> City/Town: </h3>
          <a-select default-value="Edinburgh" style="width: 140px" size="small ">
            <a-select-option value="Edinburgh">Edinburgh</a-select-option>
          </a-select>
        </div>
        <div class="flex-row between" style="align-items: center;margin-top: 10px">
          <h3> Postcode: </h3>
          <a-select default-value="EH7 9HE" style="width: 140px" size="small ">
            <a-select-option value="EH7 9HE">EH7 9HE</a-select-option>
          </a-select>
        </div>
        <a-input style="width: 100%;margin-top: 10px">
                <span slot="suffix">
                  <a-icon type="audio"/>
                  <a-icon type="search"/>
                </span>
        </a-input>
        <a-radio-group name="radioGroup" :default-value="1" style="width: 100%;margin-top: 10px">
          <a-radio :value="1">Game name</a-radio>
          <a-radio :value="2">Game Type</a-radio>
        </a-radio-group>
      </a-col>
      <a-col :span="14" class="" style="padding:20px;">
        <div class="flex-row">
          <div>
            <h2>No interested in result?</h2>
            <h2>Start to create a new club!</h2>
          </div>
          <a-button style="margin-left: 4em">Create</a-button>
        </div>
        <div class="flex-column between" style="margin-top: 2vh;">
          <div class="flex-column border" v-for="item in clubs"
               style="width: 100%;padding:10px ;justify-content: start;margin-top: 2px">
            <div class="flex-row around" style="width: 100%;align-items: center">
              <img :src="item.image" alt="" style="height: 10vh">
              <div style="width: 70%;margin-left: 2vh">
                <p>Description: {{ item.briefDescription }}</p>
                <div class="flex-row">
                  <div>Address:</div>
                  <div class="flex-column">
                    <span v-for="address in item.address">&nbsp;{{ address }}</span>
                  </div>
                </div>
              </div>
              <div style="margin-right: 4vh;margin-top: 4vh">
                <a-button @click="clickClubDetail(item)">View</a-button>
              </div>
            </div>
            <div class="flex-row" style="margin-top: 10px;width:50%;justify-content: space-around">
              <h3>{{ item.name }}</h3>
              <h4 style="font-weight: 700"> {{ item.content }}</h4>
            </div>
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
  name: 'Club',
  components: {},
  props: {
    showClubDetail: {
      type: Function
    }
  },
  data() {
    return {
      clubs: [
        {
          id: '1',
          name: 'Club 1',
          briefDescription: 'xxxxxxxxxxxx',
          address: ['xxxxx', 'xxxxx', 'xxxxx'],
          content: '0.1 miles away',
          image: require('@/assets/image1.png')
        },
        {
          id: '2',
          name: 'Club 2',
          briefDescription: 'xxxxxxxxxxxx',
          address: ['xxxxx', 'xxxxx', 'xxxxx'],
          content: '0.5 miles away',
          image: require('@/assets/image1.png')
        },
        {
          id: '3',
          name: 'Club 3',
          briefDescription: 'xxxxxxxxxxxx',
          address: ['xxxxx', 'xxxxx', 'xxxxx'],
          content: '0.8 miles away',
          image: require('@/assets/image1.png')
        },
      ],
    }
  },
  mounted() {
  },
  methods: {
    clickClubDetail(e) {
      this.$emit('showClubDetail',e)
    }
  }
}
</script>
