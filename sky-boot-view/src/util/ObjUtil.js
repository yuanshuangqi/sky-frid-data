const ObjUtil = {
    isNotEmpty(obj){
        return obj !== undefined && obj !== null && obj !== ''
    },
    isEmpty(obj){
        return !this.isNotEmpty(obj)
    },
    isNotBlank(str){
        return this.isNotEmpty(str) && str.trim() !== ''
    }
}
export default ObjUtil
