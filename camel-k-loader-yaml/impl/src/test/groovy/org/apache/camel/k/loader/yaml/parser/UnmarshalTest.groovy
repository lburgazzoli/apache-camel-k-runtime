/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.camel.k.loader.yaml.parser

import org.apache.camel.k.loader.yaml.support.TestSupport
import org.apache.camel.model.UnmarshalDefinition

class UnmarshalTest extends TestSupport {

    def "definition with data format"() {
        when:
            def processor = toProcessor('unmarshal', '''
                 json: 
                   library: Gson
            ''')
        then:
            with(processor, UnmarshalDefinition) {
                dataFormatType.dataFormatName == 'json-gson'
            }
    }

    def "definition with data format block"() {
        when:
            def processor = toProcessor('unmarshal', '''
                 data-format-type:
                   json:
                     library: Gson
            ''')
        then:
            with(processor, UnmarshalDefinition) {
                dataFormatType.dataFormatName == 'json-gson'
            }
    }

    def "definition with default data format"() {
        when:
            def processor = toProcessor('unmarshal', '''
                 json: {}
            ''')
        then:
            with(processor, UnmarshalDefinition) {
                dataFormatType.dataFormatName == 'json-jackson'
            }
    }

    def "definition with default data format block"() {
        when:
            def processor = toProcessor('unmarshal', '''
                 data-format-type:
                   json: {}
            ''')
        then:
            with(processor, UnmarshalDefinition) {
                dataFormatType.dataFormatName == 'json-jackson'
            }
    }
}
