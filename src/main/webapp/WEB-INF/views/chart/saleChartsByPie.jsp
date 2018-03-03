<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <%@include file="/WEB-INF/views/commons/common_head.html" %>
    <script type="text/javascript" src="/js/jquery/plugins/echarts/echarts-all.js"></script>
    <title>PSS-销售报表-饼状</title>
    <style>
        .alt td {
            background: black !important;
        }
    </style>
</head>
<body>
<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div id="main" style="height:500px;width: 750px"></div>
<script type="text/javascript">
    // 基于准备好的dom，初始化echarts图表
    var myChart = echarts.init(document.getElementById('main'));
    /* var option = {
     title: {
     text: '销售总额',
     subtext: '${groupTypeName}',
     x: 'center'
     },
     tooltip: {
     trigger: 'item',
     formatter: "{a} <br/>{b} : {c} ({d}%)"
     },
     legend: {
     orient: 'vertical',
     x: 'left',
     data:${groupTypeNames}
     },
     toolbox: {
     show: true,
     feature: {
     mark: {show: true},
     dataView: {show: true, readOnly: false},
     magicType: {
     show: true,
     type: ['pie', 'funnel'],
     option: {
     funnel: {
     x: '25%',
     width: '50%',
     funnelAlign: 'left',
     max: ${maxSaleAmount}
     }
     }
     },
     restore: {show: true},
     saveAsImage: {show: true}
     }
     },
     calculable: true,
     series: [
     {
     name: '访问来源',
     type: 'pie',
     radius: '55%',
     center: ['50%', '60%'],
     data:${saleAmounts}
     }
     ]
     };*/
    option = {
        title: {
            text: '销售总额',
            subtext: '${groupTypeName}',
            x: 'center'
        },
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            x: 'center',
            y: 'bottom',
            data:${groupTypeNames}
        },
        toolbox: {
            show: true,
            feature: {
                mark: {show: true},
                dataView: {show: true, readOnly: false},
                magicType: {
                    show: true,
                    type: ['pie', 'funnel']
                },
                restore: {show: true},
                saveAsImage: {show: true}
            }
        },
        calculable: true,
        series: [
            {
                name: '半径模式',
                type: 'pie',
                radius: [20, 110],
                center: ['25%', 200],
                roseType: 'radius',
                width: '40%',       // for funnel
                max: ${maxSaleAmount},            // for funnel
                itemStyle: {
                    normal: {
                        label: {
                            show: false
                        },
                        labelLine: {
                            show: false
                        }
                    },
                    emphasis: {
                        label: {
                            show: true
                        },
                        labelLine: {
                            show: true
                        }
                    }
                },
                data:${saleAmounts}
            },
            {
                name: '面积模式',
                type: 'pie',
                radius: [30, 110],
                center: ['75%', 200],
                roseType: 'area',
                x: '50%',               // for funnel
                max: ${maxSaleAmount},                // for funnel
                sort: 'ascending',     // for funnel
                data:${saleAmounts}
            }
        ]
    };

    // 为echarts对象加载数据
    myChart.setOption(option);
</script>
</body>
</html>
