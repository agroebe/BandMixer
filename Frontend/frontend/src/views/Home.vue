<template>
    <b-card
        bg-variant="dark"
        text-variant="white"
        title="Learn About BandMixer"
        img-src="https://images.unsplash.com/photo-1510915361894-db8b60106cb1?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1350&q=80"
        img-width="300"
        img-alt="Card image"
        img-left
        class="m-auto"
        style="max-width: 65rem;"
        >
        <b-card-text>BandMixer helps connect music enthusiasts of varying competencies, skill levels, and genres.</b-card-text>
        <b-card-text>BandMixer helps connect music enthusiasts.</b-card-text>
        <p>{{ testData }}</p>
    </b-card>
</template>

<script>
import axios from 'axios'

export default {
    data() {
        return {
            testData: ''
        }
    },
    mounted() {
        axios.get('https://jsonplaceholder.typicode.com/todos/1').then(response => {
            this.testData = response.data.title
        }).catch(error => {
            console.log(error)
        })
    }
}
</script>