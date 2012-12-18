/**
 * Copies all the field values of a form to another form
 */
(function($) {
    $.fn.copyValuesTo = function($other) {
        return this.each(function() {
            $(':input[name]', this).each(function() {

                $('[name=' + $(this).attr('name') +']', $other).val($(this).val())
                $('[name=' + $(this).attr('name') +']', $other).attr("checked", $(this).attr("checked"));

            })
        })
    }
}(jQuery))