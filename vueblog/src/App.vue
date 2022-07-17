<template>
  <nav>
    <router-link to="/blogs">Blogs</router-link> |
    <router-link to="/login">Login</router-link> |
    <router-link to="/blog/add">BlogAdd</router-link> |
    <router-link to="/blog/1">BlogDetail</router-link> |
    <router-link to="/blog/1/edit">BlogEdit</router-link> |
  </nav>

  <hr>
  <router-view/>
</template>

<style>

nav {
  padding: 30px;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
}

nav a.router-link-exact-active {
  color: #42b983;
}
</style>
