# AliasShareConsumer

Purebred provisions keys on Android devices such that applications can use the keys via the Android [Key Chain API](https://developer.android.com/reference/android/security/KeyChain). User confirmation is required before an app can use a key via the KeyChain API. The choosePrivateKeyAlias method is used to solicit confirmation. An alias can be passed to choosePrivateKeyAlias to preselect that value in the dialog box presented to the user. However, the KeyChain provides no means to enumerate aliases or certificates that are available via the KeyChain to facilitate passing an alias to choosePrivateKeyAlias. 

Recent versions of the Purebred Registration app features a content provider that can be used by applications desiring to use Purebred-provisioned keys via the KeyChain API. The content provider allows applications to obtain a list of aliases and certificates that were provisioned via Purebred to enable a better user experience when using choosePrivateKey.

Recent versions of the Purebred Registration app feature three broadcast intents: UPDATE\_COMPLETE, RECOVERY\_COMPLETE and RESET\_INITIATED. Each of these actions would result from use of the User Key Management view in the Purebred Registration application. UPDATE\_COMPLETE is broadcast as the profile resulting from clicking Download Configuration is processed. Typically this includes between 1 and 3 SCEP payloads that result in new certificates and 1 recovered encryption certificate. RECOVERY\_COMPLETE is broadcast when the profile resulting from clicking Recover Keys is processed. Typically this includes between 1 and 3 recovered encryption keys (the upper limit is configurable on the portal but it is unlikely to be bumped beyond three). RESET\_INITIATED is broadcast when the Reset Purebred button is clicked and a reset operation is started. This cleans up the key store to best extent possible and resets state of the app but does not change the state on the portal (that must be done by an agent prior to re-enrolling). 

AliasShareConsumer provides sample code that illustrates usage of the content provider to obtain various combinations of information from the Purebred application. 

Note, the alias sharing feature was introduced with Purebred v1.5 and later. Unofficial apks for download can be found at https://pbpki.com/pl.

