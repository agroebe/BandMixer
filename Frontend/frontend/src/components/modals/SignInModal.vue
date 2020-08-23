<template>
    <b-modal id="sign-in-modal" size="lg" title="Sign In">
        <b-form>
            <label>Username</label>
            <b-input v-model="username"></b-input>

            <label for="text-password">Password</label>
            <b-input type="password" v-model="password"></b-input>
        </b-form>

        <template v-slot:modal-footer="{ cancel }">
            <p class="mr-auto redirect" @click="redirect()">Not registered yet? Register here.</p>
            <b-button size="sm" variant="success" @click="signIn()">Sign In</b-button>
            <b-button size="sm" variant="danger" @click="cancel()">Cancel</b-button>
        </template>
    </b-modal>
</template>

<script>
export default {
    data() {
        return {
            username: '',
            password: ''
        }
    },
    methods: {
        signIn() {
            this.$bvModal.hide('sign-in-modal')
            this.$bvToast.toast('Welcome back to BandMixer, ' + this.username + '!', {
                toaster: 'b-toaster-top-center',
                title: 'Sign In Successful',
                variant: 'success'
            })
        },
        redirect() {
            this.$bvModal.hide('sign-in-modal')
            this.$bvModal.show('register-modal')
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