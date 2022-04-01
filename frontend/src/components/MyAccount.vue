<template>
  <div>
    <a-col class="flex-column">
      <a-col class="flex-row" style="justify-content: space-around">
        <a-col flex="1">
          <a-avatar size="large" icon="user"/>
        </a-col>
        <a-col flex="5" style="text-align: left">
          <p>User name : {{ userInfo.name }}</p>
          <p>ID Number : {{ userInfo.email }}</p>
        </a-col>
        <a-col flex="1">
          <a-button shape="round" style="background-color:#b4b2b2; color: #fff">Edit Profile</a-button>
        </a-col>
      </a-col>
      <a-col class="flex-row" style="justify-content: space-around;width: 70%;margin-top: 10px">
        <a-button size="small" shape="round" style="width: 30%" @click="clickFollowing">My Following</a-button>
        <a-button size="small" shape="round" style="width: 30%" @click="clickFans">My Fans</a-button>
        <a-button size="small" shape="round" style="width: 30%" @click="clickClub">My Club</a-button>
      </a-col>
      <a-col class="flex-row" style="justify-content: space-around;width: 70%;margin-top: 10px">
        <a-button size="small" shape="round" style="width: 30%;border: 3px solid #000">My Posts</a-button>
        <a-button size="small" shape="round" style="width: 30%;border: 3px solid #000">My Comments</a-button>
        <a-button size="small" shape="round" style="width: 30%;border: 3px solid #000">My Favorite</a-button>
      </a-col>
      <a-col class="flex-row border around" style="align-items: center;width: 100%;margin-top: 10px;height: 30vh">
        <h3>Post/Comment/Favorite Content</h3>
      </a-col>
    </a-col>
  </div>
</template>
<script>
export default {
  name: 'MyAccount',
  props: {
    showFollowing: {
      type: Function
    },
    showFans: {
      type: Function
    },
    showClub: {
      type: Function
    },
    userInfo: {
      type: Object,
      default: () => {
        return {
          name: '',
          email: '',
        }
      }
    },
    isLogin: {
      type: Boolean,
      default: false
    }
  },
  components: {},
  mounted() {
  },
  methods: {
    clickFollowing() {
      this.$emit('showFollowing')
    },
    clickFans() {
      this.$emit('showFans')
    },
    clickClub() {
      this.$emit('showClub')
    },
  }
}

</script>
