<%@ include file="../all.jsp" %>
<div class="do">
    <div><span class="sp_text white">Password</span></div>

    <p class="note lg_text white">Visit your email inbox, we sent you a 'reset link' that is valid for one hour only</p>

    <p class="note lg_text white">Don't forget to visit your <span class="lg_header">spam</span> folder.
    Need help? email <a href="mailto:<%=Vars.supportEmail%>"><span class="lg_header white"><%=Vars.supportEmail%></span></a></p>

    <p class="note smd_text white">
        For the safety and privacy of all our members we encrypt all password with the SHA-512 algorithm 100,000 times with a
        random salt that is 256 characters long &mdash; for this reason we can not recover your last password
    </p>
    <p class="note smd_text white">
        We need to validate your identity via email before you can set a new password for your account
    </p>
</div>