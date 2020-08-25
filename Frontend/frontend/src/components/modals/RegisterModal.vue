<template>
    <b-modal id="register-modal" size="lg" title="Register An Account">
        <b-form>
            <label>Username</label>
            <b-input v-model="username"></b-input>

            <label for="text-password">Password</label>
            <b-input type="password" v-model="password"></b-input>

            <label for="text-password">Confirm password</label>
            <b-input type="password" v-model="passwordConfirmation"></b-input>
        </b-form>

        <template v-slot:modal-footer="{ cancel }">
            <p class="mr-auto redirect" @click="redirect()">Already have an account? Sign in here.</p>
            <b-button size="sm" variant="success" @click="register()">Register</b-button>
            <b-button size="sm" variant="danger" @click="cancel()">Cancel</b-button>
        </template>
    </b-modal>
</template>

<script>
export default {
    data() {
        return {
            username: '',
            password: '',
            passwordConfirmation: ''
        }
    },
    methods: {
        register() {
            console.log("Attempted register")
        },
        redirect() {
            this.$bvModal.hide('register-modal')
            this.$bvModal.show('sign-in-modal')
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