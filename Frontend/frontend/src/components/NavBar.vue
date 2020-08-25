<template>
    <div>
        <b-navbar type="dark" variant="dark">
            <b-navbar-brand href="#" @click="redirect()">BandMixer</b-navbar-brand>
            <b-navbar-nav class="ml-auto">
                <b-button class="mr-2" v-b-modal.sign-in-modal squared variant="primary">Sign In</b-button>
                <b-button v-b-modal.register-modal squared variant="secondary">Register</b-button>
            </b-navbar-nav>
        </b-navbar>
        <SignInModal/>
        <RegisterModal/>
    </div>
</template>

<script>
import SignInModal from './modals/SignInModal.vue'
import RegisterModal from './modals/RegisterModal.vue'

export default {
    components: {
        SignInModal,
        RegisterModal
    },
    methods: {
        redirect() {
            this.$router.push('/')
        }
    }
}
</script>