package com.google.ads.mediation.customevent;

import com.amazonaws.javax.xml.stream.dtd.nonvalidating.DTDGrammar;
import com.google.ads.mediation.MediationServerParameters;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class CustomEventServerParameters extends MediationServerParameters {

    @MediationServerParameters.Parameter(name = "class_name", required = DTDGrammar.QNameHashtable.UNIQUE_STRINGS)
    public String className;

    @MediationServerParameters.Parameter(name = "label", required = DTDGrammar.QNameHashtable.UNIQUE_STRINGS)
    public String label;

    @MediationServerParameters.Parameter(name = "parameter", required = false)
    public String parameter = null;
}
