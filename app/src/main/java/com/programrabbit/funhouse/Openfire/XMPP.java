package com.programrabbit.funhouse.Openfire;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.programrabbit.funhouse.LoginActivity;
import com.programrabbit.funhouse.SignupActivity;
import com.programrabbit.funhouse.TestMenuActivity;

import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;
import org.jivesoftware.smackx.iqregister.AccountManager;
import org.jxmpp.jid.DomainBareJid;
import org.jxmpp.jid.impl.JidCreate;
import org.jxmpp.jid.parts.Localpart;
import org.jxmpp.stringprep.XmppStringprepException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class XMPP {
    AbstractXMPPConnection connection;
    boolean isAccountCreated;

    Context mContext;

    private static final String HOSTNAME = "192.168.100.5";
    private static final String SERVICENAME = "lap-sidewinderx37";

    public XMPP(Context mContext) {
        this.mContext = mContext;

        XMPPTCPConnectionConfiguration.Builder config = XMPPTCPConnectionConfiguration
                .builder();
        config.setSecurityMode(ConnectionConfiguration.SecurityMode.disabled);
        DomainBareJid serviceName = null;
        try {
            serviceName = JidCreate.domainBareFrom(SERVICENAME);
        } catch (XmppStringprepException e) {
            e.printStackTrace();
        }
        config.setServiceName(serviceName);
        config.setHost(HOSTNAME);
        config.setPort(5222);
        config.setDebuggerEnabled(true);
        XMPPTCPConnection.setUseStreamManagementResumptiodDefault(true);
        XMPPTCPConnection.setUseStreamManagementDefault(true);
        config.setSendPresence(true);
        config.setDebuggerEnabled(true);
        config.setSendPresence(true);
        config.setCompressionEnabled(false);
        connection = new XMPPTCPConnection(config.build());
        try {
            connection.connect();
        } catch (SmackException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XMPPException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void loginUser(String username, String password){


        try {
            connection.login(username, password);
            Log.i("LOGIN", "Yey! We're connected to the Xmpp server!");
            Intent i = new Intent(mContext, TestMenuActivity.class);
            mContext.startActivity(i);

        } catch (XMPPException | SmackException | IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
        }

    }


    public void registerUser(String username, String password, String email, String phone) throws InterruptedException, XMPPException, SmackException, IOException {

        AccountManager accountManager = AccountManager.getInstance(connection);
        Map<String, String> attributes = new HashMap<>();
        attributes.put("name", username);
        attributes.put("email", email);
        attributes.put("phone",phone);
        //Log.d("test", "test2");
        try {
            if (accountManager.supportsAccountCreation()) {
                accountManager.sensitiveOperationOverInsecureConnection(true);
                accountManager.createAccount(Localpart.from(username),password, attributes);
                isAccountCreated = true;

                //Toast.makeText(mContext, "Account has been created!", Toast.LENGTH_SHORT).show();


                Intent i = new Intent(mContext, LoginActivity.class);
                mContext.startActivity(i);

                Log.i("test", "inside register user");
            }
        } catch (Exception e) {
            Log.e("test", e.toString());
            //Toast.makeText(mContext, "Username already taken!", Toast.LENGTH_SHORT).show();
            //.printStackTrace(e);
        }
    }
}
