<template>
    <b-jumbotron header="MusicMate" lead="Let us help you find your jam!">
        <div class="search">
            <b-form class="d-inline" inline @submit="onSubmit" @reset="onReset">
            <b-form-select id="input" v-model="form.instrument" :options="instruments" required></b-form-select>
            <b-form-input v-model="form.location" placeholder="ZIP code or location" required></b-form-input>
            <b-button type="submit" variant="primary">Submit</b-button>
            </b-form>
        </div>
    </b-jumbotron>
</template>