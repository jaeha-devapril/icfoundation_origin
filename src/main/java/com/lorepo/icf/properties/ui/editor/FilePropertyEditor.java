package com.lorepo.icf.properties.ui.editor;

import com.google.gwt.user.client.ui.DialogBox;
import com.lorepo.icf.properties.IProperty;
import com.lorepo.icf.widgets.IFileUploadListener;
import com.lorepo.icf.widgets.UploadFileDlg;
import com.lorepo.icf.widgets.mediabrowser.IMediaProvider;
import com.lorepo.icf.widgets.mediabrowser.IMediaProvider.MediaType;
import com.lorepo.icf.widgets.mediabrowser.UploadInfo;

class FilePropertyEditor extends DlgPropertyEditor{

	private final IMediaProvider mediaProvider;
	
	public FilePropertyEditor(IProperty property, IMediaProvider provider) {
		super(property);
		
		this.mediaProvider = provider;
	}

	protected DialogBox createEditor() {

		return new UploadFileDlg(new IFileUploadListener() {
			public void onFileUploaded(String json) {
				fileUploaded(json);
			}
		});
	}

	private void fileUploaded(String json) {
		UploadInfo uploadInfo = UploadInfo.create(json);
		mediaProvider.addMediaUrl(MediaType.FILE, uploadInfo.getHref(),
				uploadInfo.getFileName(), uploadInfo.getContentType());
		getProperty().setValue(uploadInfo.getHref());
	}
	
}
