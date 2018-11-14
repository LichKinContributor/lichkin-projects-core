package com.lichkin.application.apis.UploadPhoto;

import javax.validation.constraints.NotBlank;

import com.lichkin.framework.beans.impl.LKRequestBean;
import com.lichkin.framework.constraints.BASE64;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class I extends LKRequestBean {

	/** 头像 */
	@BASE64
	@NotBlank
	private String photo;

}
