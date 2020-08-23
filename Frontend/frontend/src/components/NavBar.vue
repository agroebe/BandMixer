<template>
    <div>
        <b-navbar toggleable="lg">
            <b-navbar-brand href="#">BandMixer</b-navbar-brand>
            <b-navbar-nav class="ml-auto">
                <b-button class="mr-2" v-b-modal.sign-in-modal>Sign In</b-button>
                <b-button v-b-modal.register-modal>Register</b-button>
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
    }
}
</script>