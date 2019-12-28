import Vue from 'vue'
import {
    Button,
    Form,
    FormItem,
    Input,
    Container,
    Header,
    Aside,
    Main,
    Menu,
    Submenu,
    MenuItem,
    Breadcrumb,
    BreadcrumbItem,
    DropdownMenu,
    Dropdown,
    DropdownItem,
    Table,
    TableColumn,
    Pagination,
    Dialog,
    Message,
    MessageBox,
    Cascader,
    Select,
    Tree,
    Popover,
    Scrollbar,
    CheckboxGroup,
    Checkbox,
    Radio,
    RadioGroup,
    Row,
    Col
} from 'element-ui'

Vue.use(Button)
Vue.use(Form)
Vue.use(FormItem)
Vue.use(Input)
Vue.use(Container)
Vue.use(Header)
Vue.use(Aside)
Vue.use(Main)
Vue.use(Menu)
Vue.use(Submenu)
Vue.use(MenuItem)
Vue.use(Breadcrumb)
Vue.use(BreadcrumbItem)
Vue.use(DropdownMenu)
Vue.use(Dropdown)
Vue.use(DropdownItem)
Vue.use(Table)
Vue.use(TableColumn)
Vue.use(Pagination)
Vue.use(Dialog)
Vue.use(Cascader)
Vue.use(Select)
Vue.use(Tree)
Vue.use(Popover)
Vue.use(Scrollbar)
Vue.use(CheckboxGroup)
Vue.use(Checkbox)
Vue.use(Radio)
Vue.use(RadioGroup)
Vue.use(Row)
Vue.use(Col)
Vue.component(Message.name, Message)
Vue.component(MessageBox.name, MessageBox)

Vue.prototype.$message = Message;
Vue.prototype.$confirm = MessageBox.confirm;
