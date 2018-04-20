package vip.xuejike.ktpl.libs

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import vip.xuejike.ktpl.PageJkKtView

data class TestDemo(var x:String);

class EditView: PageJkKtView() {
    override fun content(): String {
        val testDemo = TestDemo(x="111");
        return createHTML().div {
            jkFormItem {
                jkInput(bind = testDemo::x)
                if (testDemo.x==""){
                    jkInput(bind = testDemo::x)
                }

                jkInput(bind = testDemo::x)
                jkInput(bind = testDemo::x)
                jkInput(bind = testDemo::x)
            }
        }
    }

}