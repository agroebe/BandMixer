<template>
    <b-jumbotron header="BandMixer" lead="Let us help you find your jam!">
        <p>Start your search below</p>
        <div class="search">
            <b-form class="d-inline" inline @submit="onSubmit">
                <b-form-select class="mr-2" id="input" v-model="form.instrument" :options="instruments" required></b-form-select>
                <b-form-input class="mr-2" v-model="form.location" placeholder="ZIP code or location" required></b-form-input>
                <b-button type="submit" variant="primary">Submit</b-button>
            </b-form>
        </div>
    </b-jumbotron>
</template>

<script>
export default {
    name: 'Showcase',
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