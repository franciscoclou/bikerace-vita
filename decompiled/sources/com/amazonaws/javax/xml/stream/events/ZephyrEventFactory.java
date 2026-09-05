package com.amazonaws.javax.xml.stream.events;

import com.amazonaws.javax.xml.a.a;
import com.amazonaws.javax.xml.a.b;
import com.amazonaws.javax.xml.stream.Location;
import com.amazonaws.javax.xml.stream.XMLEventFactory;
import java.util.Iterator;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class ZephyrEventFactory extends XMLEventFactory {
    Location location = null;

    @Override // com.amazonaws.javax.xml.stream.XMLEventFactory
    public Attribute createAttribute(String str, String str2) {
        AttributeImpl attributeImpl = new AttributeImpl(str, str2);
        if (this.location != null) {
            attributeImpl.setLocation(this.location);
        }
        return attributeImpl;
    }

    @Override // com.amazonaws.javax.xml.stream.XMLEventFactory
    public Attribute createAttribute(b bVar, String str) {
        return createAttribute(bVar.c(), bVar.a(), bVar.b(), str);
    }

    @Override // com.amazonaws.javax.xml.stream.XMLEventFactory
    public Attribute createAttribute(String str, String str2, String str3, String str4) {
        AttributeImpl attributeImpl = new AttributeImpl(str, str2, str3, str4, (String) null);
        if (this.location != null) {
            attributeImpl.setLocation(this.location);
        }
        return attributeImpl;
    }

    @Override // com.amazonaws.javax.xml.stream.XMLEventFactory
    public Characters createCData(String str) {
        CharacterEvent characterEvent = new CharacterEvent(str, true);
        if (this.location != null) {
            characterEvent.setLocation(this.location);
        }
        return characterEvent;
    }

    @Override // com.amazonaws.javax.xml.stream.XMLEventFactory
    public Characters createCharacters(String str) {
        CharacterEvent characterEvent = new CharacterEvent(str);
        if (this.location != null) {
            characterEvent.setLocation(this.location);
        }
        return characterEvent;
    }

    @Override // com.amazonaws.javax.xml.stream.XMLEventFactory
    public Comment createComment(String str) {
        CommentEvent commentEvent = new CommentEvent(str);
        if (this.location != null) {
            commentEvent.setLocation(this.location);
        }
        return commentEvent;
    }

    @Override // com.amazonaws.javax.xml.stream.XMLEventFactory
    public DTD createDTD(String str) {
        DTDEvent dTDEvent = new DTDEvent(str);
        if (this.location != null) {
            dTDEvent.setLocation(this.location);
        }
        return dTDEvent;
    }

    @Override // com.amazonaws.javax.xml.stream.XMLEventFactory
    public EndDocument createEndDocument() {
        EndDocumentEvent endDocumentEvent = new EndDocumentEvent();
        if (this.location != null) {
            endDocumentEvent.setLocation(this.location);
        }
        return endDocumentEvent;
    }

    @Override // com.amazonaws.javax.xml.stream.XMLEventFactory
    public EndElement createEndElement(b bVar, Iterator it) {
        return createEndElement(bVar.c(), bVar.a(), bVar.b());
    }

    @Override // com.amazonaws.javax.xml.stream.XMLEventFactory
    public EndElement createEndElement(String str, String str2, String str3) {
        EndElementEvent endElementEvent = new EndElementEvent(str, str2, str3);
        if (this.location != null) {
            endElementEvent.setLocation(this.location);
        }
        return endElementEvent;
    }

    @Override // com.amazonaws.javax.xml.stream.XMLEventFactory
    public EndElement createEndElement(String str, String str2, String str3, Iterator it) {
        EndElementEvent endElementEvent = new EndElementEvent(str, str2, str3);
        if (it != null) {
            while (it.hasNext()) {
                endElementEvent.addNamespace((Namespace) it.next());
            }
        }
        if (this.location != null) {
            endElementEvent.setLocation(this.location);
        }
        return endElementEvent;
    }

    @Override // com.amazonaws.javax.xml.stream.XMLEventFactory
    public EntityReference createEntityReference(String str, EntityDeclaration entityDeclaration) {
        EntityReferenceEvent entityReferenceEvent = new EntityReferenceEvent(str, entityDeclaration);
        if (this.location != null) {
            entityReferenceEvent.setLocation(this.location);
        }
        return entityReferenceEvent;
    }

    @Override // com.amazonaws.javax.xml.stream.XMLEventFactory
    public Characters createIgnorableSpace(String str) {
        CharacterEvent characterEvent = new CharacterEvent(str, false, true);
        if (this.location != null) {
            characterEvent.setLocation(this.location);
        }
        return characterEvent;
    }

    @Override // com.amazonaws.javax.xml.stream.XMLEventFactory
    public Namespace createNamespace(String str) {
        NamespaceImpl namespaceImpl = new NamespaceImpl(str);
        if (this.location != null) {
            namespaceImpl.setLocation(this.location);
        }
        return namespaceImpl;
    }

    @Override // com.amazonaws.javax.xml.stream.XMLEventFactory
    public Namespace createNamespace(String str, String str2) {
        NamespaceImpl namespaceImpl = new NamespaceImpl(str, str2);
        if (this.location != null) {
            namespaceImpl.setLocation(this.location);
        }
        return namespaceImpl;
    }

    @Override // com.amazonaws.javax.xml.stream.XMLEventFactory
    public ProcessingInstruction createProcessingInstruction(String str, String str2) {
        ProcessingInstructionEvent processingInstructionEvent = new ProcessingInstructionEvent(str, str2);
        if (this.location != null) {
            processingInstructionEvent.setLocation(this.location);
        }
        return processingInstructionEvent;
    }

    @Override // com.amazonaws.javax.xml.stream.XMLEventFactory
    public Characters createSpace(String str) {
        CharacterEvent characterEvent = new CharacterEvent(str);
        if (this.location != null) {
            characterEvent.setLocation(this.location);
        }
        return characterEvent;
    }

    @Override // com.amazonaws.javax.xml.stream.XMLEventFactory
    public StartDocument createStartDocument() {
        StartDocumentEvent startDocumentEvent = new StartDocumentEvent();
        if (this.location != null) {
            startDocumentEvent.setLocation(this.location);
        }
        return startDocumentEvent;
    }

    @Override // com.amazonaws.javax.xml.stream.XMLEventFactory
    public StartDocument createStartDocument(String str) {
        StartDocumentEvent startDocumentEvent = new StartDocumentEvent(str);
        if (this.location != null) {
            startDocumentEvent.setLocation(this.location);
        }
        return startDocumentEvent;
    }

    @Override // com.amazonaws.javax.xml.stream.XMLEventFactory
    public StartDocument createStartDocument(String str, String str2) {
        StartDocumentEvent startDocumentEvent = new StartDocumentEvent(str, str2);
        if (this.location != null) {
            startDocumentEvent.setLocation(this.location);
        }
        return startDocumentEvent;
    }

    @Override // com.amazonaws.javax.xml.stream.XMLEventFactory
    public StartDocument createStartDocument(String str, String str2, boolean z) {
        StartDocumentEvent startDocumentEvent = new StartDocumentEvent(str, str2, z);
        if (this.location != null) {
            startDocumentEvent.setLocation(this.location);
        }
        return startDocumentEvent;
    }

    @Override // com.amazonaws.javax.xml.stream.XMLEventFactory
    public StartElement createStartElement(b bVar, Iterator it, Iterator it2) {
        return createStartElement(bVar.c(), bVar.a(), bVar.b(), it, it2);
    }

    @Override // com.amazonaws.javax.xml.stream.XMLEventFactory
    public StartElement createStartElement(String str, String str2, String str3) {
        StartElementEvent startElementEvent = new StartElementEvent(str, str2, str3);
        if (this.location != null) {
            startElementEvent.setLocation(this.location);
        }
        return startElementEvent;
    }

    @Override // com.amazonaws.javax.xml.stream.XMLEventFactory
    public StartElement createStartElement(String str, String str2, String str3, Iterator it, Iterator it2) {
        return createStartElement(str, str2, str3, it, it2, null);
    }

    @Override // com.amazonaws.javax.xml.stream.XMLEventFactory
    public StartElement createStartElement(String str, String str2, String str3, Iterator it, Iterator it2, a aVar) {
        StartElementEvent startElementEvent = new StartElementEvent(str, str2, str3);
        startElementEvent.addAttributes(it);
        startElementEvent.addNamespaceAttributes(it2);
        startElementEvent.setNamespaceContext(aVar);
        if (this.location != null) {
            startElementEvent.setLocation(this.location);
        }
        return startElementEvent;
    }

    @Override // com.amazonaws.javax.xml.stream.XMLEventFactory
    public void setLocation(Location location) {
        this.location = location;
    }
}
