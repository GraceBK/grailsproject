package fr.mbds.grails

class CronJob {
    MessageService messageService
    static triggers = {
        simple name: 'messageCronTrigger', startDelay: 10000, repeatInterval: 30000, repeatCount: -1//cronExpression: "0 0 4 * * ?"
    }

    static description = "This cron suppresses the unread messages at 4:00am everyday"

    def execute() {
        // execute job
        def cleanMessages = Message.findAllByLu(true)
        cleanMessages.each {
            it.delete()
            println("Message ${it.id} has been purged")
        }
    }
}
