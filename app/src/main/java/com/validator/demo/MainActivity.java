package com.validator.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.jingyong.validator.Validator;
import com.jingyong.validator.ValidatorBuilder;
import com.jingyong.validator.format.Check;
import com.jingyong.validator.format.CheckField;
import com.jingyong.validator.format.Email;
import com.jingyong.validator.format.Mobile;
import com.jingyong.validator.format.base.Max;
import com.jingyong.validator.format.base.Min;
import com.jingyong.validator.format.base.NotBlank;
import com.jingyong.validator.format.base.Pattern;
import com.jingyong.validator.format.base.Size;
import com.jingyong.validator.rule.IWarningProvider;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Mobile(warning = "手机号不正确1")
    TextView mTV;

    @Mobile(warning = "手机号不正确2")
    String mText = "15022729147";

    @Email(warning = "邮箱格式不正确1")
    String mEmail;

    @NotBlank(warning = "这是空")
    @CustomeAnn
    TextView mEmailText;

    @Max(value = 10, warning = "超过了10")
    int Value = 20;



    @Pattern(value = "^[0-9]*$", warning = "正则格式不正确")
    String mPattern;

    @Size(min = 0, max = 2, warning = "list大小不正确")
    List<String> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new ValidatorBuilder().setApplication(getApplication()).setWarningProvider(new WarningProvider()).build();

        Validator.inject(this);

        mEmailText = findViewById(R.id.tv_email);
        mEmailText.setText("123");
        mTV = findViewById(R.id.tv_text);
        mTV.setText("13652020143");

        mEmail = "@";
        mPattern = "123";
        test("11", 8);
    }

    @Check
    @CheckField({"mTV", "mText", "mEmail", "mEmailText", "mPattern", "Value"})
    public void test(@CustomeAnn String s,
                     @Min(value = 10, warning = "小于10") int i) {

    }

    class WarningProvider implements IWarningProvider {

        @Override
        public void show(String s) {
            Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
        }
    }
}
