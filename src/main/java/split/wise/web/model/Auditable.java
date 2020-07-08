package split.wise.web.model;


import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class Auditable<U> {

    @CreatedDate
    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    protected Date createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    protected Date updatedAt;

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date creationDate) {
        this.createdAt = creationDate;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date lastModifiedDate) {
        this.updatedAt = lastModifiedDate;
    }

}
