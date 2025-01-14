package jblog.vo;

import java.time.LocalDateTime;

public class PostVo {
	private Long id;
	private String contents;
	private LocalDateTime regDate;
	private Long categoryId;
	
    public PostVo() {
    }

    public PostVo(Long id, String contents, LocalDateTime regDate, Long categoryId) {
        this.id = id;
        this.contents = contents;
        this.regDate = regDate;
        this.categoryId = categoryId;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public LocalDateTime getRegDate() {
		return regDate;
	}

	public void setRegDate(LocalDateTime regDate) {
		this.regDate = regDate;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	@Override
	public String toString() {
		return "PostVo [id=" + id + ", contents=" + contents + ", regDate=" + regDate + ", categoryId=" + categoryId
				+ "]";
	}
}
