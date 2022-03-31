<template>
  <div>
    <a-row class="flex-column" style="width: 100%;">
      <a-col class="flex-row">
        <a-col style="flex: 2">
          <img :src="image" alt="" style="width: 100%">
        </a-col>
        <a-col style="flex: 6" class="flex-column" :offset="1">
          <a-col class="flex-row" style="align-items: baseline;">
            <h1>Game 1 </h1>
            <a-tag size="small" style="margin-left: 20px">Follow</a-tag>
          </a-col>
          <a-col class="flex-row" style="align-items: baseline;">
            <h3>Followers : 21K</h3>
          </a-col>
          <a-col class="flex-row" style="align-items: baseline;">
            <h3>Posts : 1.2K</h3>
          </a-col>
        </a-col>
      </a-col>
      <a-col class="border flex-column" style="padding: 10px">
        <a-col class="flex-row" style="align-items: center;justify-content: space-around">
          <a-tabs style="width: 22%" :animated="false">
            <a-tab-pane key="Recent" tab="Recent"/>
            <a-tab-pane key="Hot" tab="Hot"/>
          </a-tabs>
          <a-button>Categories</a-button>
          <a-input placeholder="Search community..." style="width: 30%">
                <span slot="suffix">
                  <a-icon type="audio"/>
                  <a-icon type="search"/>
                </span>
          </a-input>
          <a-button type="primary" @click="createPost">Create a new post</a-button>
        </a-col>
        <a-table :columns="tableColumns" :data-source="tableData" bordered :pagination="false">
          <template slot="topic" slot-scope="text,record">
            <a-icon type="vertical-align-top" v-if="record.top" style="color: red"/>
            <span>{{ text }}</span>
          </template>
        </a-table>
      </a-col>
      <div class="flex-row" style="margin: 10px auto;justify-content: center">
        <a-pagination :default-current="6" :total="100"/>
      </div>
    </a-row>
  </div>
</template>
<script>

export default {
  name: 'CommunityInfo',
  props: {
    showCreatePost: {
      type: Function,
    }
  },
  data() {
    return {
      image: require('@/assets/image1.png'),
      tableColumns: [
        {
          title: 'Topic',
          dataIndex: 'topic',
          scopedSlots: {customRender: 'topic'},
          align: 'center',
        },
        {
          title: 'Author',
          dataIndex: 'author',
          align: 'center',
        },
        {
          title: 'Views',
          dataIndex: 'views',
          align: 'center',
        },
        {
          title: 'Links',
          dataIndex: 'links',
          align: 'center',
        },
        {
          title: 'Replies',
          dataIndex: 'replies',
          align: 'center',
        },
        {
          title: 'Last active',
          dataIndex: 'lastActive',
          align: 'center',
        },
      ],
      tableData: [
        {
          key: '1',
          topic: 'New Official FAQs and Errata Document',
          author: 'Moderator',
          views: '9876',
          links: '321',
          replies: '87',
          lastActive: '1 hours ag0',
          top: true,
        },
        {
          key: '2',
          topic: 'New Player Guides',
          author: 'Moderator',
          views: '8973',
          links: '632',
          replies: '83',
          lastActive: '12 hours ago',
          top: true,
        },
        {
          key: '3',
          topic: 'How to get black crystal',
          author: 'User 1',
          views: '123',
          links: '15',
          replies: '5',
          lastActive: '1 minutes ago',
          top: false,
        },
        {
          key: '4',
          topic: 'new roles equipment?',
          author: 'User 2',
          views: '201',
          links: '23',
          replies: '8',
          lastActive: '30 minutes ago',
          top: false,
        },
      ],
    }
  },
  mounted() {
  },
  methods: {
    createPost(e) {
      this.$emit('showCreatePost',e)
    },
  }
}
</script>
