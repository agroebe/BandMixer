<template>
  <div id="app">
    <NavBar/>
    <Showcase/>
    <router-view/> <!-- Automatically injects templates from current route -->
  </div>
</template>

<script>
import NavBar from './components/NavBar.vue'
import Showcase from './components/Showcase.vue'

export default {
  components: {
    NavBar,
    Showcase
  },
  mounted() {
    console.log('mounted!')
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
</style>
