<template>
  <div>
    <Header></Header>

    <div class="mblog">
      <h2> {{ blog.title }}</h2>
      <router-link :to="{name: 'BlogEdit', params: {blogId: blog.id}}" >
        编辑
      </router-link>

      <el-divider></el-divider>
      <div class="markdown-body" v-html="blog.content"></div>
    </div>

  </div>
</template>

<script>
import 'github-markdown-css'
import Header from "../components/Header"

export default {
  name: "BlogDetail.vue",
  components: {
    Header
  },
  data() {
    return {
      blog: {
        id: "",
        title: "",
        content: "",
        userId: 0
      },
      ownBlog: false
    }
  },
  created() {
    const blogId = this.$route.params.blogId
    console.log(blogId)
    this.axios.get('/blog/' + blogId).then(res => {
      const blog = res.data.data
      this.blog.id = blog.id
      this.blog.title = blog.title

      const markdownIt = require("markdown-it");
      const md = new markdownIt();

      this.blog.content = md.render(blog.content)
      this.ownBlog = (this.blog.userId === this.$store.getters.getUser.id)
    })
  }
}
</script>

<style scoped>
.mblog {
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
  width: 100%;
  min-height: 700px;
  padding: 20px 15px;
}
</style>