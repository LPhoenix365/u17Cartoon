package rst.framework.model.entity;

import android.view.Gravity;

import java.io.Serializable;

import rst.framework.widget.DialogClickInterface;
import rst.framework.widget.DialogType;

public class DialogExchangeModel extends BaseEntity {
    public DialogExchangeModelBuilder dialogExchangeModelBuilder;

    public DialogExchangeModel(DialogExchangeModelBuilder DialogExchangeModelBuilder) {
        this.dialogExchangeModelBuilder = DialogExchangeModelBuilder;
    }

    public DialogType getDialogType() {
        return dialogExchangeModelBuilder.dialogType;
    }

    public CharSequence getDialogContext() {
        return dialogExchangeModelBuilder.dialogContext;
    }

    public String getPostiveText() {
        return dialogExchangeModelBuilder.postiveText;
    }

    public String getNegativeText() {
        return dialogExchangeModelBuilder.negativeText;
    }

    public String getTag() {
        return dialogExchangeModelBuilder.tag;
    }

    public String getSingleText() {
        return dialogExchangeModelBuilder.singleText;
    }


    public boolean isBackable() {
        return dialogExchangeModelBuilder.isBackable;
    }

    public int getGravity() {
        return dialogExchangeModelBuilder.gravity;
    }

    public DialogClickInterface getClickInterface() {
        return dialogExchangeModelBuilder.clickInterface;
    }

    public static class DialogExchangeModelBuilder implements Serializable {
        /**
         *
         */
        private static final long serialVersionUID = -3685432164096360693L;
        /**
         * 弹出框类型
         */
        private DialogType dialogType = DialogType.SINGLE;
        /**
         * 弹出框内容
         */
        private CharSequence dialogContext = "";
        /**
         * 是否显示标题
         */
        private boolean hasTitle = true;
        /**
         * 确认按键
         */
        private String postiveText = "";
        /**
         * 取消按键
         */
        private String negativeText = "";
        /**
         * 单按键
         */
        private String singleText = "";
        /**
         * tag
         */
        private String tag = "";
        /**
         * 是否可取消
         */
        private boolean isBackable = true;

        private transient DialogClickInterface clickInterface;

        private int gravity = Gravity.CENTER;

        public DialogExchangeModelBuilder(DialogType ctripHDDialogType, String tag) {
            this.dialogType = ctripHDDialogType;
            this.tag = tag;
        }

        public DialogExchangeModelBuilder setTag(String tag) {
            this.tag = tag;
            return this;
        }

        public DialogExchangeModelBuilder setDialogType(DialogType ctripHDDialogType) {
            this.dialogType = ctripHDDialogType;
            return this;
        }

        public DialogExchangeModelBuilder setDialogContext(CharSequence dialogContext) {
            this.dialogContext = dialogContext;
            return this;
        }

        public DialogExchangeModelBuilder setHasTitle(boolean hasTitle) {
            this.hasTitle = hasTitle;
            return this;
        }

        public DialogExchangeModelBuilder setPostiveText(String postiveText) {
            this.postiveText = postiveText;
            return this;
        }

        public DialogExchangeModelBuilder setNegativeText(String negativeText) {
            this.negativeText = negativeText;
            return this;
        }

        public DialogExchangeModelBuilder setSingleText(String singleText) {
            this.singleText = singleText;
            return this;
        }

        public DialogExchangeModelBuilder setBackable(boolean isBackable) {
            this.isBackable = isBackable;
            return this;
        }

        public DialogExchangeModelBuilder setGravity(int gravity) {
            this.gravity = gravity;
            return this;
        }

        public DialogExchangeModelBuilder setClickInterface(DialogClickInterface clickInterface) {
            this.clickInterface = clickInterface;
            return this;
        }

        public DialogExchangeModel creat() {
            return new DialogExchangeModel(this);
        }

    }
}
