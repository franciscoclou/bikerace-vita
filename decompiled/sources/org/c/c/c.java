package org.c.c;

/* JADX INFO: compiled from: BaseStringExtractorImpl.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class c implements b {
    @Override // org.c.c.b
    public String a(org.c.d.c cVar) {
        c(cVar);
        return String.format("%s&%s&%s", org.c.g.b.a(cVar.k().name()), org.c.g.b.a(cVar.h()), b(cVar));
    }

    private String b(org.c.d.c cVar) {
        org.c.d.e eVar = new org.c.d.e();
        eVar.a(cVar.e());
        eVar.a(cVar.f());
        eVar.a(new org.c.d.e(cVar.a()));
        return eVar.c().a();
    }

    private void c(org.c.d.c cVar) {
        org.c.g.c.a(cVar, "Cannot extract base string from a null object");
        if (cVar.a() == null || cVar.a().size() <= 0) {
            throw new org.c.b.c(cVar);
        }
    }
}
