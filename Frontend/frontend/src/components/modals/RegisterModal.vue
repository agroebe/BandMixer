<template>
    <b-modal id="register-modal" size="lg" title="Register An Account">
        <template v-slot:modal-footer="{ cancel }">
            <p class="mr-auto redirect" @click="redirect()">Already have an account? Sign in here.</p>
            <b-button size="sm" variant="success" @click="register()">Register</b-button>
            <b-button size="sm" variant="danger" @click="cancel()">Cancel</b-button>
        </template>
    </b-modal>
</template>

<script>
export default {
    methods: {
        register() {
            console.log("Attempted register")
        },
        cancel() {
            console.log("Cancel")
        },
        redirect() {
            console.log('going')
        }
    }
}
</script>

<style scoped>
.redirect {
    color: blue;
}

.redirect:hover {
    text-decoration: underline;
    cursor: pointer;
}
</style>