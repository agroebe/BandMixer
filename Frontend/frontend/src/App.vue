<template>
  <div id="app">
    <NavBar/>
    <Showcase/>
    <router-view/> <!-- Automatically injects templates from current route -->
    <Footer/>
  </div>
</template>

<script>
import NavBar from './components/NavBar.vue'
import Showcase from './components/Showcase.vue'
import Footer from './components/Footer.vue'

export default {
  components: {
    NavBar,
    Showcase,
    Footer
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
