package com.tesji.rafael_work.model

class ModelPago {
    var nombre: String? = null
    var fecha = 0.0
    var ene = 0.0
    var feb = 0.0
    var mar = 0.0
    var abr = 0.0
    var may = 0.0
    var jun = 0.0
    var jul = 0.0
    var ago = 0.0
    var sep = 0.0
    var oct = 0.0
    var nov = 0.0
    var dic = 0.0
    var sueldo = 0.0
    fun calcularVenta(): String {
        //ASIGNACION DE VARIABLES
        var com:Double
        var res:Double
        var aguinaldo:Double
        var remuneracion:Double = 0.0
        var prima:Double = 0.0
        var febrero:Double
        var sueldototal:Double = 0.0
        var sueldomes:Double
        var sal:Double
        var pri:Double
        var agui:Int
        var aguinaldototal:Double
        var dias:Double
        //COMPROBACION DE FECHA
        res = fecha % 4
        if(res == 0.0){
            com = 366.0
            com = 45.0 / com
            febrero = 29.0
        }else{
            com = 365.0
            com = 45.0 / com
            febrero = 28.0
        }
        sueldomes = sueldo / 100
        sal = sueldomes * 7.3
        pri = sueldomes * 5.8
        //USAR VALOR DE COMPROBACIÓN PARA CALCULAR EL AGUINALDO
        dias = ene + feb + mar + abr + may + jun + jul + ago + sep + oct + nov + dic
        aguinaldo = dias * com
        if(ene==31.0){
            sueldototal=sueldototal+sueldo
            remuneracion = remuneracion + sal
            prima = prima + pri
        }else{
            sueldototal=sueldototal+sueldo
        }
        if(feb==febrero){
            sueldototal=sueldototal+sueldo
            remuneracion = remuneracion + sal
            prima = prima + pri
        }else{
            sueldototal=sueldototal+sueldo
        }
        if(mar==31.0){
            sueldototal=sueldototal+sueldo
            remuneracion = remuneracion + sal
            prima = prima + pri
        }else{
            sueldototal=sueldototal+sueldo
        }
        if(abr==30.0){
            sueldototal=sueldototal+sueldo
            remuneracion = remuneracion + sal
            prima = prima + pri
        }else{
            sueldototal=sueldototal+sueldo
        }
        if(may==31.0){
            sueldototal=sueldototal+sueldo
            remuneracion = remuneracion + sal
            prima = prima + pri
        }else{
            sueldototal=sueldototal+sueldo
        }
        if(jun==30.0){
            sueldototal=sueldototal+sueldo
            remuneracion = remuneracion + sal
            prima = prima + pri
        }else{
            sueldototal=sueldototal+sueldo
        }
        if(jul==31.0){
            sueldototal=sueldototal+sueldo
            remuneracion = remuneracion + sal
            prima = prima + pri
        }else{
            sueldototal=sueldototal+sueldo
        }
        if(ago==31.0){
            sueldomes = sueldo / 100
            sal = sueldomes * 7.3
            pri = sueldomes * 5.8
            sueldototal=sueldototal+sueldo
            remuneracion = remuneracion + sal
            prima = prima + pri
        }else{
            sueldototal=sueldototal+sueldo
        }
        if(sep==30.0){
            sueldomes = sueldo / 100
            sal = sueldomes * 7.3
            pri = sueldomes * 5.8
            sueldototal=sueldototal+sueldo
            remuneracion = remuneracion + sal
            prima = prima + pri
        }else{
            sueldototal=sueldototal+sueldo
        }
        if(oct==31.0){
            sueldomes = sueldo / 100
            sal = sueldomes * 7.3
            pri = sueldomes * 5.8
            sueldototal=sueldototal+sueldo
            remuneracion = remuneracion + sal
            prima = prima + pri
        }else{
            sueldototal=sueldototal+sueldo
        }
        if(nov==30.0){
            sueldomes = sueldo / 100
            sal = sueldomes * 7.3
            pri = sueldomes * 5.8
            sueldototal=sueldototal+sueldo
            remuneracion = remuneracion + sal
            prima = prima + pri
        }else{
            sueldototal=sueldototal+sueldo
        }
        if(dic==31.0){
            sueldomes = sueldo / 100
            sal = sueldomes * 7.3
            pri = sueldomes * 5.8
            sueldototal=sueldototal+sueldo
            remuneracion = remuneracion + sal
            prima = prima + pri
        }else{
            sueldototal=sueldototal+sueldo
        }
        //agui = aguinaldo.toInt()
        aguinaldototal = aguinaldo * sueldo
        var total:Double = remuneracion+sueldototal+prima+aguinaldototal
        return """
               Empleado: $nombre
               Remuneracion por mes completo: ${"$"}$remuneracion
               Sueldo: ${"$"}$sueldototal
               Prima por mes completo: ${"$"}$prima
               Prima por mes completo: $aguinaldo
               Dias de aguinaldo: $dias
               Sueldo por aguinaldo: ${"$"}$aguinaldototal
               TOTAL A PAGAR: ${"$"}$total
               """.trimIndent()
        /*
        Ene31
        Feb29
        Mar31
        Abr30
        May31
        Jun30
        Jul31
        Ago31
        Sep30
        Oct31
        Nov30
        Dic31
        */
    }
}