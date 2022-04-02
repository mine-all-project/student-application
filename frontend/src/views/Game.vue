<template>
  <div>
    <a-col class="flex-row">
      <a-col flex="2" class="flex-row">
        <a-col flex="1" class="flex-column">
          <a-button size="small" style="margin-top: 10px">All</a-button>
          <a-button size="small" style="margin-top: 10px">Categories</a-button>
          <a-button size="small" style="margin-top: 10px">Scope ↑</a-button>
          <a-button size="small" style="margin-top: 10px">Scope ↓</a-button>
          <a-button size="small" style="margin-top: 10px">A-Z</a-button>
          <a-button size="small" style="margin-top: 10px">Filter</a-button>
        </a-col>
        <a-col flex="1" class="flex-column" style="align-items: start;justify-content: start;margin-top: 2em">
          <a-checkbox style="margin-left: 8px;" checked>shooting</a-checkbox>
          <a-checkbox>team</a-checkbox>
          <a-checkbox>car</a-checkbox>
          <a-checkbox checked>girl</a-checkbox>
          <a-checkbox>boy</a-checkbox>
          <a-checkbox>sports</a-checkbox>
        </a-col>
      </a-col>
      <a-col flex="6" class="flex-column">
        <a-input placeholder="Search" style="width: 40%">
              <span slot="suffix">
                <a-icon type="search"/>
              </span>
        </a-input>
        <a-col class="border" style="padding: 20px;margin-top: 10px">
          <h3>Games</h3>
          <a-col class="flex-row" style="width: 100%;flex-wrap: wrap;justify-content: flex-start">
            <a-col class="flex-column" v-for="item in games"
                   style="width: 20%;align-items: center;margin-top: 10px;padding: 20px ">
              <img v-if="item.image":src="item.image.virtualPath" alt="" style="width: 100%;height: 15vh" @click="clickGameDetail(item)"/>
              <h3 style="margin-top: 10px">{{ item.name }}</h3>
              <a-button>Add in collection</a-button>
            </a-col>
          </a-col>
        </a-col>
      </a-col>
    </a-col>
  </div>
</template>
<script>
export default {
  name: 'Game',
  props: {
    games: {
      type: Array,
      default: () => {
        return []
      }
    },
    showGameDetail: {
      type: Function,
    }
  },
  components: {},
  mounted() {
  },
  methods: {
    clickGameDetail(e) {
      this.$emit('showGameDetail', e)
    }
  }
}

</script>
