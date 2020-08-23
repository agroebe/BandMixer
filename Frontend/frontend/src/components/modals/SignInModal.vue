<template>
    <b-modal id="sign-in-modal" size="lg" title="Sign In">
        <template v-slot:modal-footer="{ cancel }">
            <p class="mr-auto redirect" @click="console.log('hi')">Not registered yet? Register here.</p>
            <b-button size="sm" variant="success" @click="signIn()">Sign In</b-button>
            <b-button size="sm" variant="danger" @click="cancel()">Cancel</b-button>
        </template>
    </b-modal>
</template>

<script>
export default {
    methods: {
        signIn() {
            console.log("Attempted sign in")
        },
        cancel() {
            console.log("Cancel")
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