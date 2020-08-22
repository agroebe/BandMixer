<template>
  <div id="app">
    <NavBar/>
    <b-jumbotron header="MusicMate" lead="Let us help you find your jam!">
      <div class="search">
        <b-form class="d-inline" inline @submit="onSubmit" @reset="onReset">
          <b-form-select id="input" v-model="form.instrument" :options="instruments" required></b-form-select>
          <b-form-input v-model="form.location" placeholder="ZIP code or location" required></b-form-input>
          <b-button type="submit" variant="primary">Submit</b-button>
        </b-form>
      </div>
    </b-jumbotron>
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
  data() {
    return {
      form: {
        instrument: null,
        location: ''
      },
      instruments: [ { text: 'What are you looking for?', value: null }, 'Guitar', 'Bass', 'Drums', 'Piano', 'Keyboard' ]
    }
  },
  methods: {
    onSubmit(evt) {
      // evt.preventDefault()
      alert(JSON.stringify(this.form))
    },
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
