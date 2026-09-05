package org.c.c;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.c.d.k;

/* JADX INFO: compiled from: TokenExtractorImpl.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class g implements a, f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final Pattern f1613a = Pattern.compile("oauth_token=([^&]+)");
    private static final Pattern b = Pattern.compile("oauth_token_secret=([^&]*)");
    private static final Pattern c = Pattern.compile("oauth_token_duration=([\\d]*)");

    @Override // org.c.c.a, org.c.c.f
    public k a(String str) {
        org.c.g.c.a(str, "Response body is incorrect. Can't extract a token from an empty string");
        return new k(a(str, f1613a), a(str, b), Long.parseLong(a(str, c)), str);
    }

    private String a(String str, Pattern pattern) {
        Matcher matcher = pattern.matcher(str);
        if (matcher.find() && matcher.groupCount() >= 1) {
            return org.c.g.b.b(matcher.group(1));
        }
        throw new org.c.b.b("Response body is incorrect. Can't extract token and secret from this: '" + str + "'", null);
    }
}
